package com.yc.homelibrary.model.implement;

import com.yc.commonlibrary.base.UrlConfig;
import com.yc.commonlibrary.bean.ApiResult;
import com.yc.commonlibrary.http.RetrofitUtils;
import com.yc.commonlibrary.interfaces.OnRequestCallBackListener;
import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.homelibrary.model.HomeModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;


public class HomeModelImpl implements HomeModel {
    @Override
    public void loadDate(final onCallBackListener callBackListener) {
        Map map = new HashMap();
        map.put("key", "4a216a3fde4361f175aa2678dada199b");
        map.put("type", "top");
        RetrofitUtils.getInstance().get(UrlConfig.NEWS_URL, map, new OnRequestCallBackListener<ApiResult>() {
            @Override
            public void onSuccess(ApiResult result, String type) {
                if (result.getResult() != null)
                    //通过回调将数据返回给 model层
                    callBackListener.onSuccess(result);
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
        instance.get(UrlConfig.NEWS_URL, bulider, new OnRequestCallBackListener<ApiResult>() {
            @Override
            public void onSuccess(ApiResult result, String type) {
                if (result.getResult() != null)
                    //通过回调将数据返回给 model层
                    callBackListener.onSuccess(result);
            }

            @Override
            public void onFailed(String e, String type) {
                callBackListener.onFailed(e);
            }
        });

    }
}
