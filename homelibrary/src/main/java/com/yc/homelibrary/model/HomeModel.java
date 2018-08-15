package com.yc.homelibrary.model;


import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.commonlibrary.model.BaseModel;
import com.yc.homelibrary.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2018/7/25.
 */

public interface HomeModel extends BaseModel {
    //使用回调的方式返回数据，不用返回值返回的原因是因为防止在请求数据的时候方法在等待返回值而是ui变的卡顿
    void loadDate1(onCallBackListener callBackListener);
}
