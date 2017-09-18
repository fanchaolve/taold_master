package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.api.ApiServiveImpl;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.Session;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.DeviceUtils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
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
                feedbackCommit();
                break;
        }
    }

    private void feedbackCommit() {
        Call<Result_Api<String>> call = new ApiServiveImpl().memberCreateUserFeedback(this, mEtFeedback.getText().toString());
        Callexts.Unneed_sessionPost(call, new PostCallback<FeedbackActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                Log.e("0000", "提交成功");
            }

            @Override
            public void failCallback() {

            }
        });

    }
}
