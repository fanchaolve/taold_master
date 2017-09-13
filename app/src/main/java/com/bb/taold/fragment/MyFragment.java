package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bb.taold.R;
import com.bb.taold.activitiy.my.LoanRecordsActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.utils.AppManager;

import butterknife.OnClick;


/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class MyFragment extends BaseFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
//        mTvLayouttitleTitle.setText("我的");
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @OnClick({})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_my_header:
                AppManager.getInstance().showActivity(LoanRecordsActivity.class,null);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
