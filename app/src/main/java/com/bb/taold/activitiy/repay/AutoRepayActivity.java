package com.bb.taold.activitiy.repay;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.activitiy.repay
 * Class Name AutoRepayActivity
 */

public class AutoRepayActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_repay;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("自动还款");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }
}
