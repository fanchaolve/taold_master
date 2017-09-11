package com.bb.taold.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.fragment.Authorized_IdFragment;
import com.bb.taold.fragment.Authorized_SesameFragment;
import com.bb.taold.fragment.Mobile_Phone_OperatorsFragment;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.widget.stepview.StepsView;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.activitiy
 * <p>
 * 说明：诸多认证（）
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/9
 * <p>
 * ==============================================
 */

public class AuthInfoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.btn_back)
    ImageButton btn_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.sp_step)
    StepsView sp_step;

    private AuthInfo info;


    @Override
    public int getLayoutId() {
        return R.layout.activity_authinfo;
    }

    @Override
    public void initView() {

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        info = new AuthInfo();
        if (bundle != null) {
            if (bundle.containsKey(Constants.AUTHOINFO)) {
                info = (AuthInfo) bundle.getSerializable(Constants.AUTHOINFO);
            }
        }

        btn_back.setVisibility(View.VISIBLE);
        tv_title.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        tv_title.setText(getString(R.string.apply_loan));
        if (info.getFlag() == 1 || info.getFlag() == 2) {
            AppManager.getInstance().showAuthFace(this, new Authorized_IdFragment());
        } else if (info.getFlag() == 3) {
            AppManager.getInstance().showAuthMoblie(this, new Mobile_Phone_OperatorsFragment());
        } else if (info.getFlag() == 4) {
            AppManager.getInstance().showAuth_Sesame(this, new Authorized_SesameFragment());
        } else if (info.getFlag() == 5) {

        }

        goStep(info.getFlag() - 1);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 步骤视图,往下走 ，刷新view
     */
    public void goStep(final int flag) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("fancl", "步骤:" + flag);
                sp_step.setcompletePosition(flag);
            }
        }, 1000);
    }
}
