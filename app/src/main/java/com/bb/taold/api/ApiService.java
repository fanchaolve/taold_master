package com.bb.taold.api;

import com.bb.taold.bean.AuthInfo;
import com.bb.taold.bean.AuthMessage;
import com.bb.taold.bean.AuthParam;
import com.bb.taold.bean.Session;
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



    //--------------------------------------------


    /**
     * 会员登陆
     *
     * @param mobile 电话
     * @param code   验证码
     * @param lonLat 经纬
     * @param type 设备类型：android 安卓 ios 苹果
     * @param device_id 设备号
     * @param version 版本号
     * @param brand 设备厂商
     * @param model 设备名称
     * @param sim sim卡电话卡
     * @param is_root 是否root权限
     * @param net_type 网络状态 2G 3G 4G
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=user.login")
    Call<Result_Api<Session>> user_login(@Field("mobile") String mobile,
                                         @Field("code") String code,
                                         @Field("lonLat") String lonLat,
                                         @Field("type") String type,
                                         @Field("device_id") String device_id,
                                         @Field("version") String version,
                                         @Field("brand") String brand,
                                         @Field("model") String model,
                                         @Field("sim") String sim,
                                         @Field("is_root") String is_root,
                                         @Field("net_type") String net_type
    );


    /**
     * 短信验证码
     *
     * @param mobile 手机号码
     * @return
     */
    @GET("/gateway?method=sms.sendLoginSmsCode")
    Call<Result_Api> sms_sendLoginSmsCode(@Query("mobile") String mobile);

    /**
     * @param session    会员ID
     * @param bankCardId 银行卡ID
     * @param amtLoan    借款金额
     * @param loanDays   借款天数
     * @return
     */
    @GET("/gateway?method=loan.applyMiniLoan")
    Call<Result_Api> applyMiniLoan(@Field("session") String session,
                                   @Field("bankCardId") String bankCardId,
                                   @Field("amtLoan") String amtLoan,
                                   @Field("loanDays") String loanDays);


    /**
     *  获取用户认证信息
     * @return
     */
    @POST("/gateway?method=member.identityAuthInfo")
    Call<Result_Api<AuthInfo>> member_identityAuthInfo();


    /**
     * 人脸识别
     *
     * @return
     */
    @POST("/gateway?method=ocr.init")
    Call<Result_Api<AuthParam>> ocr_init();



    /**
     * 人脸识别状态
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=ocr.authStatus")
    Call<Result_Api<AuthMessage>> ocr_authStatus(@Field("orderNo") String orderNo);


}
