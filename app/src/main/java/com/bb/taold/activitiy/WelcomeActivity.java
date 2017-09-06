package com.bb.taold.activitiy;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;


import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.api.ApiService;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.VersionBean;
import com.bb.taold.listener.DialogListener;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.DialogUtils;
import com.bb.taold.utils.PermissionUtil;

import retrofit2.Call;

public class WelcomeActivity extends BaseActivity {

    private ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);
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

    private VersionBean versionInfo;
    /**
     * 非强制更新 确认后直接弹出更新，不需要进入程序 否则以后更新 进入app
     */
    DialogListener listener1 = new DialogListener() {
        @Override
        public void onConfirm(Dialog dialog) {
            if (versionInfo.getLink().contains("http")) {
                // 更新程序
                Uri uri = Uri.parse(versionInfo.getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } else {
                Toast.makeText(WelcomeActivity.this, "更新地址错误", Toast.LENGTH_SHORT).show();
            }

            AppManager.getInstance().finishAllActivity();
            dialog.cancel();
        }

        @Override
        public void onCancl(Dialog dialog) {
            startPage();
            dialog.cancel();
        }
    };



    @Override
    public void initdata() {
        permissionUtil = PermissionUtil.getInstance();
        postCallback=new PostCallback() {
            @Override
            public void successCallback(Result_Api api) {

                if(api.getData() instanceof VersionBean){
                    versionInfo = (VersionBean) api.getData();
                    if (versionInfo != null && !TextUtils.isEmpty(versionInfo.getLink())) {
                        if (!TextUtils.isEmpty(versionInfo.getForce()) && versionInfo.getForce().equals("1")){
                            DialogUtils.showMsgDialogForce(WelcomeActivity.this,"有新版本更新",versionInfo.getExplain(), listener1);
                        }else {
                            DialogUtils.showMsgDialog(WelcomeActivity.this, versionInfo.getExplain(), listener1);
                        }
                        return;
                    }
                }

                startPage();
            }

            @Override
            public void failCallback() {
                startPage();
            }
        };

        updateVersionCheck();
    }


    private void startPage() {
        listener = new PermissionUtil.onPermissionGentedListener() {
            @Override
            public void onGented() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFalied() {
                finish();
            }
        };
        permissionUtil.setListener(listener);
        permissionUtil.ReadPhoneContactsTask();

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initLocationService() {

    }


    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        super.onStop();
//        // TODO Auto-generated method stub

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private  void updateVersionCheck(){
        Call<Result_Api<VersionBean>> call=service.getUpgradeInfo("1.0","0","II");
        call.enqueue(postCallback);




    }


}
