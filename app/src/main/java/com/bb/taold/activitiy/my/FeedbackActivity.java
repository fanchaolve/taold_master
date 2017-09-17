package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.btn_info)
    ImageButton mBtnInfo;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.et_feedback)
    EditText mEtFeedback;
    @BindView(R.id.tv_feedback_commit)
    TextView mTvFeedbackCommit;
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {
        mTvTitle.setText("意见反馈");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.btn_back, R.id.tv_feedback_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_feedback_commit:
                break;
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
