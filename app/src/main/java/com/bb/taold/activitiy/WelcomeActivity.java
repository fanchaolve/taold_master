package com.bb.taold.activitiy;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.Location;
import com.bb.taold.listener.exts.Act1;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.PermissionUtil;
import com.bb.taold.utils.PreferenceUtil;
import com.bb.taold.utils.gps.GPSUtil;

public class WelcomeActivity extends BaseActivity {


    private PermissionUtil.onPermissionGentedListener listener;   //权限获取


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
                        if(AppManager.getInstance().currentActivity()!=null) {
//                            WelcomeActivity.this.finish();
                            boolean preference = PreferenceUtil.bSharedPreference(mContext, PreferenceUtil.isNewUser);
                            if(preference){
                                AppManager.getInstance().showActivity(HomeActivity.class, null);
                            }else{
                                AppManager.getInstance().showActivity(BannerActivity.class, null);
                            }
                        }
                    }
                }, 2000);


            }

            @Override
            public void onFalied() {
                finish();
            }
        };
        permissionUtil.setListener(listener);
        permissionUtil.needPermission();




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateVersionCheck() {


    }


}
