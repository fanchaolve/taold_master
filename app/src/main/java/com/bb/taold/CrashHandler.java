package com.bb.taold;

import android.content.Context;
import android.util.Log;

import com.bb.taold.utils.AppManager;

/**
 * 崩溃日志 接收
 * Created by fancl
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private final static  String TAG=CrashHandler.class.getSimpleName();
    private static CrashHandler INSTANCE;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {

    }
    public static CrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }


    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }



    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        final String msg = ex.getLocalizedMessage();
        Log.e(TAG, msg);
        AppManager.getInstance().AppExit(mContext);
        return true;
    }

}
