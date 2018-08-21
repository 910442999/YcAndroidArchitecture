package com.yc.commonlibrary.interfaces;

public interface OnRequestCallBackListener<T> {
    void onSuccess(T result, String type);

    void onFailed(String e, String type);

}
