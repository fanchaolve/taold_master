package com.bb.taold.activitiy.loan;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhucheng'an on 2017/9/11.
 * Package Name com.bb.taold.activitiy
 * Class Name ApplySuccessActivity
 */

public class ApplySuccessActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_success;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("提交成功");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.btn_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_ok:
                break;
        }
    }
}
