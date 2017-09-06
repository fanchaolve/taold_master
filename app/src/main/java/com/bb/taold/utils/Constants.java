package com.bb.taold.utils;

/**
 * Created by Administrator on 2016/12/22.
 */

public final class Constants {

    public static final String SUCCESS = "0";//服务器返回成功
    public static final String OSNAME = "A";//操作系统

    public static final int SQUARE_PAGE_COUNT = 3;

    public static final int PAGE_BKTJ = 0;
    public static final int PAGE_YXHD = 1;
    public static final int PAGE_ZTLB = 2;


    public static final String TYPE = "type";


    /**
     * 1：短信验证码
     * 2：图形验证码
     */
    public static final String DXYZM = "1";
    public static final String TXYZM = "2";


    public static final String REGIST_bizType = "10";//注册
    public static final String LOGIN_bizType = "11";//登录
    public static final String FINDPWD_bizType = "12";//找回密码
    public static final String RECIVE_bizType = "13";//发送短信验证码


    public static final int BKTJ = 20;

    //C端用户
    public static final int CLIENTUSER = 10;
    //B端用户
    public static final int BOSSUSER = 20;

    public static final String BusInsurance = "20";

    public static final String personInsurance = "10";

    public static final String AllInsurance = "0";

    public static final int NOMORE = 0;//正常

    public static final int MORE = 1;//更多

    public static final String[] idTypes = {"身份证", "军官证", "护照", "驾驶证", "港澳台通行证", "回乡证"};
    public static final int[] idType_keys = {1, 2, 3, 4, 5, 6};//

    public static final String[] beinsurer1_listvalue = {"父母", "子女", "配偶", "其他关系"};//关系

    public static final int[] beinsurer1_listkey = {1, 2, 3, 9};//关系键


    public static final int  ZFBPAY = 10;

    public static final int WXPAY = 20;
    public static final int LLPAY = 30;

    public static final int GETSURED = 1;

    public static final String TRANSTATION = "insuredInfolBean";




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
}
