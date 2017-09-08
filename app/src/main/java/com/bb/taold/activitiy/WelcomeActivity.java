package com.bb.taold.activitiy;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.api.ApiService;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.Location;
import com.bb.taold.bean.VersionBean;
import com.bb.taold.listener.DialogListener;
import com.bb.taold.listener.exts.Act1;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.DialogUtils;
import com.bb.taold.utils.PermissionUtil;
import com.bb.taold.utils.gps.GPSUtil;

import retrofit2.Call;

public class WelcomeActivity extends BaseActivity {


    private PermissionUtil.onPermissionGentedListener listener;   //权限获取
    protected PermissionUtil permissionUtil;

    private PostCallback postCallback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void initdata() {
        startPage();
    }


    private void startPage() {
        permissionUtil = PermissionUtil.getInstance();
        listener = new PermissionUtil.onPermissionGentedListener() {
            @Override
            public void onGented() {

                GPSUtil.tryStart(new Act1<Location>() {
                    @Override
                    public void run(Location location) {
                        if (location != null) {
                            Log.i("fancl", "loc:" + location.latitude + "-" + location.lontitude);
                            MyApplication.latitude = location.latitude + "";
                            MyApplication.longitude = location.lontitude + "";
                            GPSUtil.tryStop();
                        }
                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("fancl", "session=" + getSession());
                        if ("".equalsIgnoreCase(getSession())) {//未登陆状态
                            AppManager.getInstance().showActivity(LoginActivity.class, null);
                        } else {//登陆状态
                            AppManager.getInstance().showActivity(HomeActivity.class, null);
                        }
                    }
                }, 10);

                WelcomeActivity.this.finish();
            }

            @Override
            public void onFalied() {
                finish();
            }
        };
        permissionUtil.setListener(listener);

        permissionUtil.GetLocationTask();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateVersionCheck() {


    }


}
