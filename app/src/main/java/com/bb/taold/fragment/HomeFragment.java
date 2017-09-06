package com.bb.taold.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bb.taold.base.BaseFragment;
import com.bb.taold.base.m.HomeModle;
import com.bb.taold.base.p.HomePresenter;
import com.bb.taold.base.v.HomeContract;


/**
 * Created by Administrator on 2016/12/20.
 */

public class HomeFragment extends BaseFragment<HomePresenter, HomeModle> implements HomeContract.View, View.OnClickListener {



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }





    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        }
    }
}
