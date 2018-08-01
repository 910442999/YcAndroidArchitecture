package com.yc.commonlibrary.model;


import com.yc.commonlibrary.interfaces.onCallBackListener;

/**
 * Created by yc on 2018/7/25.
 * 加载数据的公共方法
 */

public interface BaseModel {
    /*
    loadData采用的是无参的形式 ，接口回调的方式来获取数据的 ,而不是采用  void loadDate(List data); 这种返回值的方式
    原因是 当方法执行的时候 ，一旦数据没有返回，方法就会停留在这一直等待方法返回数据
    这样当网络延迟或者网络突然中断时,整个应用就会停留在这里，造成ui卡顿 甚至anr异常
    利用接口回调的方式来获取数据 , 用来监听数据的返回情况 这样当请求网络的时候 方法就不会一直等待返回值而造成ui卡顿 , 当监听有数据之后再经过回调将数据发送过去
     */
    void loadDate(onCallBackListener callBackListener);
}

