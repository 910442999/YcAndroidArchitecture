package com.yc.commonlibrary.interfaces;

import com.yc.commonlibrary.bean.ApiResult;

public interface OnRequestCallBackListener<T> {
    void onSuccess(ApiResult<T> string);

    void onFailed(String e);

}
