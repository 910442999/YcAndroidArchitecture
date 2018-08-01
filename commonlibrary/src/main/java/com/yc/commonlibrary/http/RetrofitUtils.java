package com.yc.commonlibrary.http;

import com.yc.commonlibrary.base.UrlConfig;
import com.yc.commonlibrary.bean.ApiResult;
import com.yc.commonlibrary.interfaces.OnRequestCallBackListener;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rbh on 2018/4/3.
 */

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static volatile RetrofitUtils instance;

    private BaseApiService mBaseApiService;


    private RetrofitUtils(Object tag) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //        // BASIC，BODY，HEADERS
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
                }
            }
        }
        return instance;
    }

    public BaseApiService getBaseApiService() {
        return mBaseApiService;
    }


    /**
     * post
     *
     * @param url
     * @param map
     * @param callBackListener
     */
    public void get(String url, Map map, final OnRequestCallBackListener callBackListener) {
        requestCallBack(url, map, callBackListener);
    }

    /**
     * post
     *
     * @param url
     * @param map
     * @param callBackListener
     */
    public void post(String url, Map map, final OnRequestCallBackListener callBackListener) {
        requestCallBack(url, map, callBackListener);
    }

    private <T> void requestCallBack(String url, Map map, final OnRequestCallBackListener<T> onRequestCallBackListener) {
        Flowable flowable = mBaseApiService.get(url, map);
        Flowable<ApiResult<T>> newsBeanFlowable1 = flowable.subscribeOn(Schedulers.io());
        newsBeanFlowable1.observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ApiResult<T>>() {

                    @Override
                    public void onNext(ApiResult<T> apiResult) {
                        onRequestCallBackListener.onSuccess(apiResult);
                    }

                    @Override
                    public void onError(Throwable t) {
                        onRequestCallBackListener.onFailed(t.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
