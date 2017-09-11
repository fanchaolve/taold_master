package com.bb.taold.activitiy.addBankCard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhucheng'an on 2017/9/11.
 * Package Name com.bb.taold.activitiy.addBankCard
 * Class Name AddBankCardActivity
 */

public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addcard;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("绑定银行卡");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_take, R.id.tv_next, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_take:
                break;
            case R.id.tv_next:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_common, null);
                dialog.setView(view);
                TextView tv_cancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
                TextView tv_content = (TextView) dialogView.findViewById(R.id.tv_content);
                tv_content.setText("检测到您添加的银行卡非借记卡，请重新添加一张借记卡");
                dialog.create();
                final AlertDialog dialog2 = dialog.show();

                dialog2.setCanceledOnTouchOutside(false);
                break;
            case R.id.btn_back:
                break;
        }
    }
}
