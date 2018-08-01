package com.yc.commonlibrary.http;

import com.yc.commonlibrary.bean.ApiResult;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by yc on 2018/4/3.
 */
public interface BaseApiService {
    @GET()
    Flowable<ApiResult> get(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<ApiResult> post(@Url String url, @QueryMap Map<String, String> map);


}
