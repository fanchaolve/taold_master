package com.bb.taold.api;

import com.bb.taold.bean.VersionBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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


    /**
     * 会员登陆
     * @param mobile 电话
     * @param code  验证码
     * @param lonLat 经纬
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=user.login")
    Call<Result_Api<String>> user_login(@Field("mobile") String mobile,
                                   @Field("code") String code,
                                   @Field("lonLat") String lonLat);


    /**
     * 短信验证码
     * @param mobile 手机号码
     * @return
     */
    @GET("/gateway?method=sms.sendLoginSmsCode")
    Call<Result_Api> sms_sendLoginSmsCode(@Query("mobile") String mobile);

    /**
     *
     * @param session 会员ID
     * @param bankCardId 银行卡ID
     * @param amtLoan 借款金额
     * @param loanDays 借款天数
     * @return
     */
    @GET("/gateway?method=loan.applyMiniLoan")
    Call<Result_Api> applyMiniLoan(@Field("session")String session,
                                @Field("bankCardId")String bankCardId,
                                @Field("amtLoan")String amtLoan,
                                @Field("loanDays")String loanDays);

}
