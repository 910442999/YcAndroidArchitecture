package com.yc.commonlibrary.view;

/**
 * Created by yc on 2018/7/24.
 * View接口类
 * view层的封装就比较简单的, 主要考虑一下工程中有哪些公共的部分 , 比如请求网络时候的加载动画, 请求网络失败时候的提醒等等
 */

public interface BaseView<V> {
    //开启加载动画
    void showLoading();

    //隐藏加载动画
    void hideLoading(boolean b);

    //错误信息
    void onError(V result);
}
