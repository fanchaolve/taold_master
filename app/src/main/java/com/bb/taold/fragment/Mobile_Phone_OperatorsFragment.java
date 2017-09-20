package com.bb.taold.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CacheUtils;
import com.bb.taold.utils.Constants;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.fragment
 * <p>
 * 说明：手机运营商授权
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/9
 * <p>
 * ==============================================
 */

public class Mobile_Phone_OperatorsFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tv_tel)
    TextView tv_tel;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mobile_phone_operators;
    }

    @Override
    public void initView() {
        tv_confirm.setOnClickListener(this);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        if(getActivity() instanceof AuthInfoActivity){
            ((AuthInfoActivity) getActivity()).goStep(2);
        }
        UserInfo info = (UserInfo) CacheUtils.getDataCache(Constants.USER_INFO);
        if(info!=null){
            String mobile = info.getMobile();
            if(!TextUtils.isEmpty(mobile)){
                tv_tel.setText(mobile);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                AppManager.getInstance().showAuth_Sesame((AuthInfoActivity)getActivity(),new Authorized_SesameFragment());
                ((AuthInfoActivity) getActivity()).goStep(3);
                break;
        }
    }
}
