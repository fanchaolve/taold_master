package com.bb.taold.activitiy.repay;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.activitiy.repay
 * Class Name RepayInfoActivity
 * 还款详情页面
 */

public class RepayInfoActivity extends BaseActivity {
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
        //立即还款按钮设置可见
        mTvConfirm.setVisibility(View.VISIBLE);
        //成功页面设置不可见
        mLlSuccess.setVisibility(View.GONE);
        //已还清设置不可见
        mLlAllpay.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }


    public void createDialog() {
        /**
         * 确定还款时弹出选择方式对话框
         */
        final Dialog dialog = new Dialog(RepayInfoActivity.this, R.style.processDialog);
        View view = RepayInfoActivity.this.getLayoutInflater().inflate(R.layout.dialog_payway, null);
        dialog.setContentView(view);

        //支付宝还款
        LinearLayout mLlAlipay = (LinearLayout) view.findViewById(R.id.ll_alipay);
        mLlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //支付宝还款监听事件
                showTip("支付宝还款");
                dialog.dismiss();
                Intent intent = new Intent(RepayInfoActivity.this,RepaySuccessActivity.class);
                startActivity(intent);
            }
        });

        //银行卡还款
        LinearLayout mLlCard = (LinearLayout) view.findViewById(R.id.ll_card);
        mLlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //银行卡还款点击事件
                dialog.dismiss();
                showTip("银行卡还款");
            }
        });

        //转账到支付宝
        LinearLayout mLlTrans = (LinearLayout) view.findViewById(R.id.ll_trans);
        mLlTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转账到支付宝点击事件
                dialog.dismiss();
                showTip("转账到支付宝");
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = MyApplication.widthPixels; // 设置宽度
        lp.y = 0;//距离底部的高度
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_in_and_out);  //添加动画
        dialog.show();
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        createDialog();
    }
}
