package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.fragment
 * <p>
 * 说明：身份认证
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/9
 * <p>
 * ==============================================
 */

public class Authorized_IdFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_authorized_idently;
    }

    @Override
    public void initView() {
        tv_confirm.setOnClickListener(this);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                AppManager.getInstance().showAuthMoblie((AuthInfoActivity) getActivity(), new Mobile_Phone_OperatorsFragment());
                ((AuthInfoActivity) getActivity()).goStep(2);
                break;
        }
    }
}
