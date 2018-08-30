package com.yc.homelibrary.view;


import com.yc.commonlibrary.view.BaseView;
import com.yc.homelibrary.bean.NewsBean;

import java.util.List;

/**
 * Created by yc on 2018/7/25.
 */

public interface IHomeView<T> extends BaseView<T> {
    //接收显示recyview中的数据
    void showhome(List<NewsBean.ResultBean.DataBean> apiResult);
}
