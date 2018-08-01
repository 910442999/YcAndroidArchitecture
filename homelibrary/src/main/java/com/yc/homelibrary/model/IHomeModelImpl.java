package com.yc.homelibrary.model;


import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.commonlibrary.model.BaseModel;
import com.yc.homelibrary.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2018/7/25.
 */

public class IHomeModelImpl implements BaseModel {

    @Override
    public void loadDate(onCallBackListener callBackListener) {
        List<HomeBean> homeBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            homeBeanList.add(new HomeBean("名字 ：" + i));
        }
        //通过回调将数据返回给 model层
        callBackListener.onComplete(homeBeanList);
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
