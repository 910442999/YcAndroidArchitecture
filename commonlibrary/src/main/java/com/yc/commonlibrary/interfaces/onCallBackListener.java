package com.yc.commonlibrary.interfaces;

/**
 * Created by yc on 2018/7/26.
 * //定义一个公共的接口来接收返回的数据 （范型传什么 回调中就接什么数据即可）
 */

public interface onCallBackListener<T> {
    //因为成功的回调中犹豫每个请求的数据都是不同的所以用范型来定义
    void onSuccess(T t);
    //而失败只会有一种情况 所以这里直接用字符串的方式来接收
    void onFailed(String e);

}
