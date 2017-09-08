package com.bb.taold.activitiy.loan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhucheng'an on 2017/9/8.
 * Package Name com.bb.taold.activitiy.loan
 * Class Name LoanConfirmActivity
 */

public class LoanConfirmActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_loanAmount)
    TextView mTvLoanAmount;
    @BindView(R.id.tv_loanDays)
    TextView mTvLoanDays;
    @BindView(R.id.tv_transFee)
    TextView mTvTransFee;
    @BindView(R.id.tv_verifyFee)
    TextView mTvVerifyFee;
    @BindView(R.id.tv_honorFee)
    TextView mTvHonorFee;
    @BindView(R.id.tv_manageFee)
    TextView mTvManageFee;
    @BindView(R.id.tv_introFee)
    TextView mTvIntroFee;
    @BindView(R.id.iv_bankicon)
    ImageView mIvBankicon;
    @BindView(R.id.tv_bankname)
    TextView mTvBankname;
    @BindView(R.id.tv_banktype)
    TextView mTvBanktype;
    @BindView(R.id.tv_cardno)
    TextView mTvCardno;

    //借款最大金额
    private int maxAmount = 20000;
    //借款最小金额
    private int minAmount = 1000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void initView() {

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

    /**
     * 金额变化时，将所有的费用修改
     * @param amount
     */
    public void setAllPrice(String amount){

    }

    @OnClick({R.id.btn_back, R.id.iv_add, R.id.tv_loanAmount, R.id.iv_delete, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_add:
                //获取当前金额
                int currentAmount = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否大于最大金额
                if ((currentAmount + 100) >= maxAmount) {
                    mTvLoanAmount.setText(maxAmount + "");
                    return;
                }
                mTvLoanAmount.setText((currentAmount + 100) + "");
                //刷新所有的费用
                setAllPrice(mTvLoanAmount.getText().toString());
                break;
            case R.id.iv_delete:
                //获取当前金额
                int currentAmountDelete = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否小于最小金额
                if (currentAmountDelete - 100 <= minAmount) {
                    mTvLoanAmount.setText(minAmount + "");
                    return;
                }
                mTvLoanAmount.setText((currentAmountDelete - 100) + "");
                //刷新所有的费用
                setAllPrice(mTvLoanAmount.getText().toString());
                break;
            case R.id.tv_confirm:

                break;
        }
    }
}
