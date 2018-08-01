package com.yc.commonlibrary.interfaces;

/**
 * Created by yc on 2018/7/26.
 * //定义一个公共的接口来接收返回的数据 （范型传什么 回调中就接什么数据即可）
 */

public interface onCallBackListener<T> {
    void onComplete(T t);
}
