package com.bb.taold.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.fragment.Auth_StateFragment;
import com.bb.taold.fragment.Authorized_IdFragment;
import com.bb.taold.fragment.Authorized_SesameFragment;
import com.bb.taold.fragment.PersonInfoFragment;
import com.bb.taold.fragment.Mobile_Phone_OperatorsFragment;

import java.util.Stack;

/**
 * Created by Administrator on 2016/11/25.
 */

public class AppManager {

    private static Stack<Activity>
            activityStack;

    private static AppManager instance;


    private static final String DEFAULT_DATA_BASEPATH = "/huibx"; // 缓存目录
    public String DEFAULT_DATA_IMAGEPATH = DEFAULT_DATA_BASEPATH + "/IMAGE"; // 小图缓存地址
    public String DEFAULT_DATA_TEMP = DEFAULT_DATA_BASEPATH + "/TEMP"; // 备份数据地址
    public String DEFAULT_DATA_BIG_IMAGEPATH = DEFAULT_DATA_BASEPATH + "/BIGIMAGE"; // 大图缓存地址

    private AppManager() {
        String rootPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        DEFAULT_DATA_IMAGEPATH = rootPath + DEFAULT_DATA_IMAGEPATH;
        DEFAULT_DATA_TEMP = rootPath + DEFAULT_DATA_TEMP;
        DEFAULT_DATA_BIG_IMAGEPATH = rootPath + DEFAULT_DATA_BIG_IMAGEPATH;
    }

    /**
     * 单一实例
     */

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);

    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack.size() > 0) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            //activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishParticularActivity(Class<?> tClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(tClass)) {
                if (activity != null) {
                    activity.finish();

                }
            }
        }


    }


    /**
     * 结束指定类名的Activity
     */

    public void finishActivity(Class<?> tClass) {

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(tClass)) {
                finishActivity(activity);
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = size - 1; i >= 0; i--) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
                activityStack.remove(i);
            }
        }
        activityStack.clear();
    }


    public boolean processBackKey(Class<? extends Activity>... classes) {
        for (Class<? extends Activity> clazz : classes) {
            if (currentActivity().getClass().getSimpleName().equals(clazz.getSimpleName())) {
                //进入最底层的页面 需要做具体操作来结束app页面，入弹出dialog
                // TODO: 2016/11/25
                return true;
            }
        }
        return false;
    }


    public void AppExit(Context context) {
        finishAllActivity();
//        ActivityManager activityManager=context.getSystemService(Context.ACTIVITY_SERVICE);
        // android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }


    /**
     * @param goActivity 跳转的页面
     * @param aBundle    带参数
     */
    public void showActivity(Class<? extends Activity> goActivity, Bundle aBundle) {
        //跳转页面
        Activity activity = currentActivity();
        Intent intent = new Intent();
        intent.setClassName(activity, goActivity.getName());
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
        activity.startActivity(intent);
    }


    /**
     * @param goActivity  跳转的页面
     * @param aBundle     带参数
     * @param requestCode
     */
    public void showActivityForResult(Class<? extends Activity> goActivity, Bundle aBundle, int requestCode) {
        Intent intent = new Intent();
        Activity activity = currentActivity();
        intent.setClassName(activity, goActivity.getName());
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void showAuthFace(BaseActivity from, Authorized_IdFragment toFragment) {
        doReplaceTran(from, toFragment, null);
    }

    public void showAuthMoblie(BaseActivity from, Mobile_Phone_OperatorsFragment toFragment) {
        doReplaceTran(from, toFragment, null);
    }

    public void showAuth_Sesame(BaseActivity from, Authorized_SesameFragment toFragment) {
        doReplaceTran(from, toFragment, null);
    }

    public void showAuth_State(BaseActivity from, Auth_StateFragment toFragment, Bundle bundle) {
        doReplaceTran(from, toFragment, bundle);
    }

    public void showBase_Info(BaseActivity from, PersonInfoFragment toFragment) {
        doReplaceTran(from, toFragment, null);
    }


    private static void doReplaceTran(BaseActivity from, BaseFragment toFragment, Bundle bundle) {
        FragmentManager fm = from.getSupportFragmentManager();
        if (fm == null)
            return;
        FragmentTransaction ft = fm.beginTransaction();
        if (bundle != null) {
            toFragment.setArguments(bundle);
        }
        ft.replace(R.id.frame_layout, toFragment);
        ft.show(toFragment);
        ft.commit();
    }


    public int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public int dp2px(Context context, double dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 退出登录
     */
    public void logout() {
        Bundle bundle =new Bundle();
        bundle.putInt("card",2);
        if (isLogin()) {
            showActivity(LoginActivity.class, bundle);
        }else {
            showActivity(LoginActivity.class, bundle);
        }
    }

    public void login(){
        if (!isLogin()) {
            showActivity(LoginActivity.class, null);
        }
    }

    /**
     * 是否登录
     */
    public boolean isLogin() {
        String session = MyApplication.getInstance().getSession();
        if (session == null || "".equalsIgnoreCase(session)) {
            return false;
        }
        return true;
    }


}
