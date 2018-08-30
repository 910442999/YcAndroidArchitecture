package com.yc.commonlibrary.http;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    Flowable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> map);
    @Multipart
    @POST()
    Flowable<ResponseBody> post(@Url String url, @PartMap Map<String, RequestBody> params);

}
