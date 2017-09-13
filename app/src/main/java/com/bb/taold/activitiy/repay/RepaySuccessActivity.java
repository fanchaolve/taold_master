package com.bb.taold.activitiy.repay;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.activitiy.repay
 * Class Name RepaySuccessActivity
 * 已还款页面
 */


public class RepaySuccessActivity extends BaseActivity{

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.ll_success)
    LinearLayout mLlSuccess;
    @BindView(R.id.ll_allpay)
    LinearLayout mLlAllpay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_repayinfo;
    }

    @Override
    public void initView() {
        //设置标题
        mTvTitle.setText("立即还款");
        mBtnBack.setVisibility(View.VISIBLE);
        //立即还款按钮设置不可见
        mTvConfirm.setVisibility(View.GONE);
        //成功页面设置可见
        mLlSuccess.setVisibility(View.VISIBLE);
        //已还清设置可见
        mLlAllpay.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
}
