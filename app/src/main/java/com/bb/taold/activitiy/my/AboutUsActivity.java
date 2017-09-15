package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

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
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    @BindView(R.id.lay_check_update)
    LinearLayout mLayCheckUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        mTvTitle.setText(R.string.about_us);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
    @OnClick({R.id.btn_back,R.id.lay_check_update})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
            case R.id.lay_check_update:

                break;
        }
    }
}
