package com.yc.commonlibrary.http;

import com.yc.commonlibrary.base.UrlConfig;
import com.yc.commonlibrary.interfaces.OnRequestCallBackListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yc on 2018/4/3.
 */

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static volatile RetrofitUtils instance;

    //定义请求服务
    private BaseApiService mBaseApiService;
    //定义公共的参数
    private static Map<String, RequestBody> params;

    private RetrofitUtils(Object tag) {

        //由于Retrofit是基于okhttp的所以，要先初始化okhttp相关配置
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //        // BASIC，BODY，HEADERS
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())//利用gson来解析数据，你也可以用其他方式解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//结合rxjava 来实现数据回调
                .baseUrl(UrlConfig.BASEURL)
                .build();
        mBaseApiService = retrofit.create(BaseApiService.class);
    }

    public static RetrofitUtils getInstance() {
        return getInstance("");
    }

    public static RetrofitUtils getInstance(Object tag) {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (null == instance) {
                    instance = new RetrofitUtils(tag);
                    params = new HashMap<String, RequestBody>();
                }
            }
        }
        return instance;
    }

    public BaseApiService getBaseApiService() {
        return mBaseApiService;
    }

    /**
     * 添加参数
     * 根据传进来的Object对象来判断是String还是File类型的参数
     */
    public RetrofitUtils addParameter(String key, Object o) {
        if (params != null && params.size() > 0)
            clear();
        if (o instanceof String) {
            RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) o);
            params.put(key, body);
        } else if (o instanceof File) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) o);
            params.put(key + "\"; filename=\"" + ((File) o).getName() + "", body);
        }
        return this;
    }

    /**
     * 构建RequestBody
     */
    public Map<String, RequestBody> bulider() {

        return params;
    }

    public void clear() {
        params.clear();
    }

    /**
     * get
     *
     * @param url                       链接
     * @param map                       参数
     * @param onRequestCallBackListener 回调监听
     */
    public void get(String url, Map map, final OnRequestCallBackListener onRequestCallBackListener) {
        get(url, map, onRequestCallBackListener, "");
    }

    /**
     * get
     *
     * @param url                       链接
     * @param map                       参数
     * @param onRequestCallBackListener 回调监听
     * @param type                      调用的方法类型(区分调用的方法的回调参数)
     */
    public void get(String url, Map map, final OnRequestCallBackListener onRequestCallBackListener, String type) {
        Flowable flowable = mBaseApiService.get(url, map);
        requestCallBack(flowable, onRequestCallBackListener, type);
    }

    /**
     * post
     *
     * @param url
     * @param map
     * @param onRequestCallBackListener
     */
    public void post(String url, Map map, final OnRequestCallBackListener onRequestCallBackListener) {
        post(url, map, onRequestCallBackListener, "");
    }

    /**
     * post
     *
     * @param url                       链接
     * @param map                       参数
     * @param onRequestCallBackListener 回调监听
     * @param type                      调用的方法类型(区分调用的方法的回调参数)
     */
    public void post(String url, Map map, final OnRequestCallBackListener onRequestCallBackListener, String type) {
        Flowable flowable = mBaseApiService.post(url, map);
        requestCallBack(flowable, onRequestCallBackListener, type);
    }

    /**
     * 处理数据请求相关功能，将flowable加入队列,通过接口回调的方式将rxjava返回的数据返回给调用者
     *
     * @param flowable                  调入的flowable
     * @param onRequestCallBackListener 回调
     * @param type                      调用方法标志，回调用
     * @param <T>                       泛型参数
     */
    public <T> void requestCallBack(Flowable<T> flowable, final OnRequestCallBackListener<T> onRequestCallBackListener, final String type) {
        Flowable<T> beanFlowable = flowable.subscribeOn(Schedulers.io());
        beanFlowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<T>() {

                    @Override
                    public void onNext(T apiResult) {
                        onRequestCallBackListener.onSuccess(apiResult, type);
                    }

                    @Override
                    public void onError(Throwable t) {
                        onRequestCallBackListener.onFailed(t.getMessage().toString(), type);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
