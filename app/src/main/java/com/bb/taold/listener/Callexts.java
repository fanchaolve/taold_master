package com.bb.taold.listener;

import com.bb.taold.MyApplication;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.api.ApiService;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.utils.AppManager;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.listener
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/13
 * <p>
 * ==============================================
 */

public class Callexts {
    //处理统一的需求
    private static void post(Call call, Callback callback, boolean isSession) {
        if (isSession) {//需要session
            if ("".equalsIgnoreCase(MyApplication.getInstance().getSession())) {
                //跳转
                AppManager.getInstance().showActivity(LoginActivity.class, null);
                return;
            }
        }
        //为了统一添加加载框,待检测是否可行,有待优化疯转
        if(callback instanceof PostCallback){
            ((PostCallback) callback).showLoading();
        }
        call.enqueue(callback);
    }

    //需要session
    public static void need_sessionPost(Call call, Callback callback) {
        post(call, callback, true);
    }

    //不需要session
    public static void Unneed_sessionPost(Call call, Callback callback) {
        post(call, callback, false);
    }


    //处理统一的需求
    private static void postMore(Callback callback, boolean isSession, Call... calls) {
        if (isSession) {//需要session
            if ("".equalsIgnoreCase(MyApplication.getInstance().getSession())) {
                //跳转
                AppManager.getInstance().showActivity(LoginActivity.class, null);
                return;
            }
        }
        for (Call call : calls) {
            call.enqueue(callback);
        }
    }

    //需要session
    public static void need_sessionPostMore(Callback callback,Call...calls) {
        postMore(callback,true, calls);
    }

    //不需要session
    public static void Unneed_sessionPostMore(Callback callback,Call...calls ) {
        postMore(callback, false,calls);
    }

}
