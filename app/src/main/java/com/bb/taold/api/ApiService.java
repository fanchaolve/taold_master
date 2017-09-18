package com.bb.taold.api;

import com.bb.taold.bean.AllBill;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.bean.AuthMessage;
import com.bb.taold.bean.AuthParam;
import com.bb.taold.bean.BandCardResult;
import com.bb.taold.bean.BillInfos;
import com.bb.taold.bean.BillItemDetail;
import com.bb.taold.bean.BillItemInfo;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.Cardinfos;
import com.bb.taold.bean.LoadRecordResponse;
import com.bb.taold.bean.LoanDetail;
import com.bb.taold.bean.ProductFee;
import com.bb.taold.bean.ProductInfo;
import com.bb.taold.bean.Session;
import com.bb.taold.bean.BillInfoDetail;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.bean.VersionBean;
import com.bb.taold.bean.WaitRepayRecord;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by fancl.
 * 服务器接口
 *
 *
 *
 * --------------------- 画重点---------------------
 * 细节注意的是注解上的method=aaaa_xxxx
 * 说明xxxx是需要过滤的(所以method的方法最好不要有_下划线的字符出现要不然就冲突了)
 * 详见银行卡绑定
 * -----------------------------------------------
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
     * 短信验证码
     *
     * @param mobile 手机号码
     * @return
     */
    @GET("/gateway?method=sms.sendLoginSmsCode")
    Call<Result_Api> sms_sendLoginSmsCode(@Query("mobile") String mobile);

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
     * 用户信息
     * @return
     */
    @POST("/gateway?method=user.info")
    Call<Result_Api<UserInfo>> user_info();



    /**
     *  银行卡绑定（添加银行
     *
     * @param bankCode 银行编号
     * @param owner    持卡人姓名
     * @param cardno   卡号
     * @param idno 身份证号码
     * @param cardName    卡名称
     * @param mobile   预留手机号
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.createNewBankCard_mobile_platform")
    Call<Result_Api<BandCardResult>> createNewBankCard(@Field("bankCode") String bankCode,
                                                       @Field("owner") String owner,
                                                       @Field("cardno") String cardno,
                                                       @Field("idno") String idno,
                                                       @Field("cardName") String cardName,
                                                       @Field("mobile") String mobile,
                                                       @Field("platform")String platform
    );

    /**
     *  设置主卡
     *
     * @param cardId 卡号Id
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.setToMaster")
    Call<Result_Api> setToMaster(@Field("cardId") String cardId);

    /**
     *  移除卡（解绑银行卡）
     *
     * @param cardId 银行卡号
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.removeCard")
    Call<Result_Api<String>> removeCard(@Field("cardId") String cardId);


    /**
     * 识别银行卡是否有效
     * @param cardNo 银行卡号
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=bank.checkBankcard")
    Call<Result_Api<CardCheck>> supportCard(@Field("cardNo") String cardNo);

    /**
     *  用户卡列表
     *
     * @param bankType 卡类型
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.bankList_bankType")
    Call<Result_Api<Cardinfos>> bankList(@Field("bankType") String bankType);



    /**
     * 小额贷款
     *
     * @param bankCardId 银行卡ID
     * @param amtLoan    借款金额
     * @param loanDays   借款天数
     * @param activityCode 活动码
     * @param couponNo    红包编号
     * @param deviceId   设备编号
     * @param latLot   经纬度
     * @param applyIp    申请IP
     * @param version   版本号
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=loan.applyMiniLoan")
    Call<Result_Api> applyMiniLoan(@Field("bankCardId") String bankCardId,
                                   @Field("amtLoan") String amtLoan,
                                   @Field("loanDays") String loanDays,
                                   @Field("latLot") String latLot,
                                   @Field("deviceId") String deviceId,
                                   @Field("activityCode") String activityCode,
                                   @Field("couponNo") String couponNo,
                                   @Field("applyIp") String applyIp,
                                   @Field("version") String version);

    /**
     * 借款申请详情
     *
     * @param loanId 借款Id
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=loan.loanDetail")
    Call<Result_Api<Session>> loanDetail(@Field("loanId") String loanId);



    /**
     * 借款金额信息（各种利息
     * @param stages 产品期数
     * @param money 借款金额
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=trade.calProductFee_session")
    Call<Result_Api<ProductFee>> calProductFee(@Field("stagesId") String stages,
                                               @Field("money") String money);

    /**
     * 查看产品信息
     * @param productCode 产品编号
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=trade.productInfo_session")
    Call<Result_Api<ProductInfo>> productInfo(@Field("productCode") String productCode);


    /**
     * 未还账单查询
     * @return
     */
    @POST("/gateway?method=bill.waitRepayRecord")
    Call<Result_Api<WaitRepayRecord>> waitRepayRecord();

    /**
     * 分页查询账单列表
     * @param offset 起始点
     * @param limit 每页的行数
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=bill.queryBillInfo")
    Call<Result_Api<AllBill>> queryBillInfo(@Field("offset") String offset,
                                                @Field("limit") String limit);


    /**
     * 分页查询账单列表
     * @param billId 账单id
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=bill.detail")
    Call<Result_Api<BillInfoDetail>> detail(@Field("billId") String billId);

    /**
     *  查看每期账单
     * @param billItemId 账单明细表id
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=bill.fundDetail")
    Call<Result_Api<BillItemInfo>> fundDetail(@Field("billItemId") String billItemId);



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


    /**
     * 提交个人资料
     * @param education
     * @param address
     * @param company
     * @param companyAddress
     * @param memberContactInfo
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.commitMemberContactInfo")
    Call<Result_Api<String>> member_commitMemberContactInfo(@Field("education") String education,
                                                             @Field("address") String address,
                                                             @Field("company") String company,
                                                             @Field("companyAddress") String companyAddress,
                                                             @Field("memberContactInfo") String memberContactInfo);
    /**
     * 借款申请列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=loan.recordList")
    Call<Result_Api<LoadRecordResponse>> loan_recordList(@Field("offset") String offset, @Field("limit") String limit);/**
     * 借款申请详情
     */
    @FormUrlEncoded
    @POST("/gateway?method=loan.loanDetail")
    Call<Result_Api<LoanDetail>> loan_recordDetail(@Field("loanId") String loanId);

    /**
     * 意见反馈
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/gateway?method=member.membercreateUserFeedback")
    Call<Result_Api<String>> memberCreateUserFeedback(
            @Field("device") String device,
            @Field("versionNumber") String versionNumber,
            @Field("feedbackContent") String feedbackContent,
            @Field("deviceNumber") String deviceNumber
    );
}
