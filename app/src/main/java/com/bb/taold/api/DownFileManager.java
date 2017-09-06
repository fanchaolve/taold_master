package com.bb.taold.api;



import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

/**
 * Created by fancl on 2017/3/24.
 * packgame--> com.bb.hbx.api
 */

public class DownFileManager {
    private final static String TAG = "DownFileManager";
    private CallBack mCallBack;
    private static DownFileManager sDownFileManager;
    private final Handler handler=new Handler(Looper.getMainLooper());
    private ExecutorService executorService;

    public interface  CallBack{

        void onSuccess(String path);
        void inProgress(int progress);

        void onFaile();

    }

    public static DownFileManager getInstance() {
        if (sDownFileManager == null) {
            sDownFileManager = new DownFileManager();
        }
        return sDownFileManager;

    }
    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService=new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("DownFileManager", false));
        }
        return executorService;
    }

    public void download(final String path, final String filePath, CallBack callBack) {
        mCallBack = callBack;
        executorService();

        executorService.submit(new Runnable() {
            boolean isSuccess;

            @Override
            public void run() {

            }
        });
    }

}
