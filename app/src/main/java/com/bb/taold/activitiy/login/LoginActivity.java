package com.bb.taold.activitiy.login;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bb.taold.R;

import com.bb.taold.base.BaseActivity;



import butterknife.BindView;


/**
 * 登录
 * Created by fancl
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View,
        View.OnClickListener{


    private final  String TAG=LoginActivity.class.getSimpleName();

    @BindView(R.id.im_close)              //右上角关闭图片按钮
            ImageView imClose;
    @BindView(R.id.et_userName)           //账号输入框
            EditText etUserName;
    @BindView(R.id.et_userPwd)            //密码输入框
            EditText etUserPwd;
    @BindView(R.id.btn_forgetPwd)          //忘记密码按钮
            TextView tvForgetPwd;
    @BindView(R.id.btn_login)             //登录按钮
            TextView btnLogin;
    @BindView(R.id.btn_register)          //注册按钮
            TextView btnRegister;
    @BindView(R.id.logo)
    ImageView logo;



    @Override
    protected void onResume() {
        super.onResume();
        Log.i("","");

    }





    @Override
    public void loginSuccess() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void initListener() {



    }

    @Override
    public void initdata() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {

            case R.id.btn_login:
                if (isverTel() && isverpassword()) {
                    mPresenter.login(etUserName.getText().toString().trim(), etUserPwd.getText().toString().trim());

                } else
                    showTip("手机号码或验证码有误");
                break;
        }
    }

    public boolean isverTel() {
        if (!TextUtils.isEmpty(etUserName.getText()) && etUserName.getText().toString().trim().length() == 11) {
            return true;
        }
        return false;
    }



    @Override
    public boolean isverpassword() {
        if(!TextUtils.isEmpty(etUserPwd.getText()) && etUserPwd.getText().toString().trim().length()>0){
            return true;
        }
        return false;
    }










    public void loginMethod(){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
