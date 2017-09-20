package com.bb.taold;

import android.app.Application;
import android.content.Context;

import com.bb.taold.bean.Location;
import com.bb.taold.bean.User;
import com.bb.taold.pushservice.IntentService;
import com.bb.taold.pushservice.PushService;
import com.bb.taold.utils.PreferenceUtil;
import com.bb.taold.utils.gps.GPSUtil;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;

import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.exception.FMException;


/**
 * Created by Administrator on 2016/12/6.
 */

public class MyApplication extends Application {


    //public LocationService locationService;
    private static MyApplication INSTANCE = null;
    private static Context context;

    public static String latitude = "";// 纬度

    public static String longitude = "";//经度

    public static String city;//城市


    public static String areaVersion = "";


    public static String DUID = "";// 设备唯一码
    public static int widthPixels = 321;// 屏幕宽度
    public static int heightPixels = 481;//屏幕高度

    public static User user;// 用户信息

    public void saveSession(String session) {
        PreferenceUtil.saveSharedPreference(this, PreferenceUtil.SESSION, session);
    }

    public String getSession() {
        return PreferenceUtil.getSharedPreference(this, PreferenceUtil.SESSION);
    }

    public static final Context getAppContext() {
        return context;
    }


    public static MyApplication getInstance() {
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        INSTANCE = this;
        GPSUtil.tryInit(this);//定位
        //推送设置
        PushManager.getInstance().initialize(this.getApplicationContext(), PushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);
        PushManager.getInstance().bindAlias(getAppContext(), "abc123");


        try {
            if (BuildConfig.DEBUG) {
                FMAgent.init(context, FMAgent.ENV_SANDBOX);
            } else {
                FMAgent.init(context, FMAgent.ENV_PRODUCTION);
            }
        } catch (FMException e) {
            e.printStackTrace();
        }
    }


}
