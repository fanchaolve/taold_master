package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.fragment
 * <p>
 * 说明：芝麻信用分
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/9
 * <p>
 * ==============================================
 */

public class Authorized_SesameFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_authorized_sesame;
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
        switch (v.getId()){
            case R.id.tv_confirm:
                AppManager.getInstance().showBase_Info((AuthInfoActivity)getActivity(),new BaseInfoFragment());
                ((AuthInfoActivity) getActivity()).goStep(4);
                break;
            default:
                break;
        }
    }
}
