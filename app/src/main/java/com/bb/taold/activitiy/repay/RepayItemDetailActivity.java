package com.bb.taold.activitiy.repay;

import android.os.Bundle;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

/**
 * Created by zhucheng'an on 2017/9/16.
 * Package Name com.bb.taold.activitiy.repay
 * Class Name RepayItemDetailActivity
 */

public class RepayItemDetailActivity extends BaseActivity{
    @Override
    public int getLayoutId() {
        return R.layout.activity_repayinfo;
    }

    @Override
    public void initView() {

        //判断状态为已还款,未还款,或已逾期
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle mBundle = getIntent().getExtras();
            //状态为已还款,隐藏按钮,显示还款银行卡信息


        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
}
