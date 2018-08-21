package com.yc.commonlibrary.http;

import com.yc.commonlibrary.bean.ApiResult;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by yc on 2018/4/3.
 */
public interface BaseApiService {
    @GET()
    Flowable<ApiResult> get(@Url String url, @QueryMap Map<String, Object> map);
    @Multipart
    @POST()
    Flowable<ApiResult> post(@Url String url, @PartMap Map<String, RequestBody> params);

}
