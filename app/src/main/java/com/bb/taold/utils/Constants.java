package com.bb.taold.utils;

/**
 * Created by Administrator on 2016/12/22.
 */

public final class Constants {

    public static final String SUCCESS = "SUCCESS";//服务器返回成功
    public static final String INVALID_SESSION = "INVALID_SESSION";//登陆失效
    public static final String OSNAME = "A";//操作系统

    public static final String APP_KEY="taolidai";
    public static final String SECRET="lU9ZCkpxiL9PW86QevPXhs";
    //支付渠道   支付宝 10 ，银行卡 20
    public static final String PAY_CHANNEL_ALIPAY="10";
    public static final String PAY_CHANNEL_CARD="20";
    //平台渠道 android 10,ios 20
    public static final String PLATFORM="10";
    public static final String AUTHOINFO="authinfo";
    //webview url
    public static final String WEBVIEW_URL = "url";
    public static final int AUTO_STATE_NO = 10;
    public static final int AUTO_STATE_OK = 20;
    //新用户flag
    public static final String NEW_USER_FLAG = "isNewUser";
    //用户信息
    public static final String USER_INFO = "userInfo";
    //卡认证后，关闭前边页面
    public static final boolean AFTER_AUTH_CLOSE = true;





    /**
     * 支付宝账号的配置项目
     */
    // 商户PID
    public static final String PARTNER = "2088621144322812";
    // 商户收款账号
    public static final String SELLER = "zhouqi@51hbx.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKzgcNenvL2j31L2Ix009R6M7jipPBHHEXcVIH3N7YrxzSGUDGHrybiSD55KNkRF563FjVVftP4PXqZiT6CgE9wvCAniMaQFluxxCaHebAaEhfvNIFqfKQArjFyyezzPu0wnh0VcZOoQD0dRGng5okiFdPbaQwkKasVJaEIUrslTAgMBAAECgYBzxcasFZAHbCQV2fJAEAQLlpO+bE9nFGp01jgxJ+RpxW77irsUJKStr1s4RTi8VhhGPmNdBkAeWEqpoHL0/d7t3uF3By4F3UWLJLhUOcHEZvwAxlvzXO5xoK5JaicRgDEgd/j/oRYDxXMZEZjkF0WygdKychB4/wMsTkT2nkYFYQJBANVH2lPKCkgEVY+NFfIG/b7NRL20TiVGmNjhPOPLWKOXuKGOLDRQeLdgBPX4BmAVIlTRHqszF+LNxGWYnTuMr7ECQQDPgNdOfkvMtOPllxGu0pk1PoFxMFhGeW/3b8YivH4MbXyj6pmaL1/Da+pW4HQBzLGsw0s7uFrZjnXQRPMxuy5DAkEAwC3vh+Kc4wmswx4A8UXlbQDvePS4GZSEc6B5SlVXuIkk38YJBNah/7IwpiZoMDm65qxMMz1lv4Nj/ZvHyfnZ8QJBAJN0z4wsG9mAwmKpFBvT9KPtoza4UELkkTiY6YhNwpU6SNSDJlx3Reotif3qywVsXOaUcaYiwAzSO86g8OFqTtkCQDEGHma/6Uu04xOVE6swrNMhXPcvg2w0gQyuLSsmzAlTsC9PariBPSygtPXCtK60tFq6ZIUZ2IEy5xkyKjEIy3Y=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnEe6hPpOhBHKP/95CLpD18M1dpZDQNCbhq898C/JdFPm0fzABpHChMCejax5fuPCaKlgbrRPYJMFvyCCXQRekbDyXPg+NqTzkY8KN0zffrxOImKa9y5KQLA1lxNzsCdPFsLqie5k0utwxDdlTHG+4mW6UqQNiwlL0QNVFUTXnywIDAQAB";

    //微信分享
    public static final String APP_ID = "wxc7473dec7d6ffdc0";
    //聚合-支付宝支付
    public static final String EBJ_ALIPAY_URL = "http://pay.ebjfinance.com/alijspay.php";
}
