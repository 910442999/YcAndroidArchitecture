package com.yc.homelibrary.model.implement;

import com.google.gson.Gson;
import com.yc.commonlibrary.base.UrlConfig;
import com.yc.commonlibrary.http.RetrofitUtils;
import com.yc.commonlibrary.interfaces.OnRequestCallBackListener;
import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.homelibrary.bean.NewsBean;
import com.yc.homelibrary.model.HomeModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class HomeModelImpl implements HomeModel {
    @Override
    public void loadDate(final onCallBackListener callBackListener) {
        Map map = new HashMap();
        map.put("key", "4a216a3fde4361f175aa2678dada199b");
        map.put("type", "top");
        RetrofitUtils.getInstance().get(UrlConfig.NEWS_URL, map, new OnRequestCallBackListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result, String type) {
                try {
                    NewsBean newsBean = new Gson().fromJson(result.string(), NewsBean.class);
                    List<NewsBean.ResultBean.DataBean> data = newsBean.getResult().getData();
                    //通过回调将数据返回给 model层
                    callBackListener.onSuccess(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(String e, String type) {
                callBackListener.onFailed(e);
            }
        });


    }

    public void loadDate1(final onCallBackListener callBackListener) {
        RetrofitUtils instance = RetrofitUtils.getInstance();
        //        instance.addParameter()
        //        Map map = new HashMap();
        instance.addParameter("key", "4a216a3fde4361f175aa2678dada199b");
        instance.addParameter("type", "top");
        Map<String, RequestBody> bulider = instance.bulider();
        instance.post(UrlConfig.NEWS_URL, bulider, new OnRequestCallBackListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result, String type) {
                try {
                    NewsBean newsBean = new Gson().fromJson(result.string(), NewsBean.class);
                    List<NewsBean.ResultBean.DataBean> data = newsBean.getResult().getData();
                    //通过回调将数据返回给 model层
                    callBackListener.onSuccess(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String e, String type) {
                callBackListener.onFailed(e);
            }
        });

    }
}
