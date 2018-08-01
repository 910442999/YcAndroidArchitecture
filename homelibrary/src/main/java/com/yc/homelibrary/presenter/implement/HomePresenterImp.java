package com.yc.homelibrary.presenter.implement;

import android.widget.Toast;

import com.yc.commonlibrary.base.UrlConfig;
import com.yc.commonlibrary.bean.ApiResult;
import com.yc.commonlibrary.http.RetrofitUtils;
import com.yc.commonlibrary.interfaces.OnRequestCallBackListener;
import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.homelibrary.bean.HomeBean;
import com.yc.homelibrary.model.HomeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePresenterImp implements HomeModel {
    @Override
    public void loadDate(final onCallBackListener callBackListener) {
        Map map = new HashMap();
        map.put("key", "4a216a3fde4361f175aa2678dada199b");
        map.put("type", "top");
        RetrofitUtils.getInstance().get(UrlConfig.NEWS_URL, map, new OnRequestCallBackListener() {
            @Override
            public void onSuccess(ApiResult apiResult) {
                if (apiResult.getResult() != null)
                    //通过回调将数据返回给 model层
                    callBackListener.onComplete(apiResult);
            }

            @Override
            public void onFailed(String e) {

            }
        });


    }

    public void loadDate1(onCallBackListener callBackListener) {
        List<HomeBean> homeBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeBeanList.add(new HomeBean("名字 ：" + i));
        }
        //通过回调将数据返回给 model层
        callBackListener.onComplete(homeBeanList);
    }
}
