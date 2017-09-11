package com.bb.taold.utils;

import android.content.Context;

import com.authreal.api.AuthBuilder;
import com.authreal.api.OnResultListener;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.utils
 * <p>
 * 说明：人脸识别的工具类
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/11
 * <p>
 * ==============================================
 */

public class AuthUtil {
    private static AuthBuilder mAuthBuilder = null;

    public static void faceAuth(Context context, String orderId,String auth_key,String notfiyUrl, OnResultListener listener){
//        String id = "demo_"+new Date().getTime();
        mAuthBuilder = new AuthBuilder(orderId, auth_key, notfiyUrl, listener);
        mAuthBuilder.faceAuth(context);
    }

}
