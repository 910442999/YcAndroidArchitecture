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

    void loadDate1(onCallBackListener callBackListener);
}
