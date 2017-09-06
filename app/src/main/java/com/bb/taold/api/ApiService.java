package com.bb.taold.api;

import com.bb.taold.bean.VersionBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by fancl.
 * 服务器接口
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("Api/upgrade")
    Call<Result_Api<VersionBean>> getUpgradeInfo(@Field("version") String version,
                                                 @Field("system") String system,
                                                 @Field("appkey") String appkey);

    @FormUrlEncoded

    @POST("https://kl.zhongmakj.com/Api/login")
    Call<Result_Api<String>> login(@Field("mobile") String mobile,
                                                 @Field("zpwd") String zpwd,
                                                 @Field("appkey") String appkey);

}
