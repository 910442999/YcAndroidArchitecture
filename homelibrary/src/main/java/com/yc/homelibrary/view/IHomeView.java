package com.yc.homelibrary.view;


import com.yc.commonlibrary.bean.ApiResult;
import com.yc.commonlibrary.view.BaseView;
import com.yc.homelibrary.bean.HomeBean;

import java.util.List;

/**
 * Created by zhanghan on 2018/7/25.
 */

public interface IHomeView<T> extends BaseView<T> {
    //显示recyview中的数据，（使用回调的方式返回数据，不用返回值返回的原因是因为防止在请求数据的时候方法在等待返回值而是ui变的卡顿）
    void showhome(ApiResult apiResult);
}
