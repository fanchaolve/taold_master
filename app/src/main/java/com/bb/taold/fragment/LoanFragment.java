package com.bb.taold.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;

import com.bb.taold.base.BaseFragment;
import com.bb.taold.base.m.Frag_LoanModel;
import com.bb.taold.base.p.Frag_LoanPresenter;
import com.bb.taold.base.v.Frag_LoanContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhucheng'an on 2017/9/7.
 * Package Name com.bb.taold.fragment
 * Class Name LoanFragment
 */

public class LoanFragment extends BaseFragment
<Frag_LoanPresenter,Frag_LoanModel> implements Frag_LoanContract.View{
    @BindView(R.id.tv_loanAmount)
    TextView mTvLoanAmount;
    @BindView(R.id.tv_allPay)
    TextView mTvAllPay;
    @BindView(R.id.tv_needPay)
    TextView mTvNeedPay;
    @BindView(R.id.iv_question)
    ImageView mIvQuestion;
    Unbinder unbinder;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    //借款最高金额
    private int maxAmount = 20000;
    //借款最低金额
    private int minAmount = 500;

    @Override
    public int getLayoutId() {
        return R.layout.activity_loan;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }



    @OnClick({R.id.iv_delete, R.id.iv_add, R.id.iv_question,R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm://申请之前
                mPresenter.memberInfo();
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
                break;
            case R.id.iv_question:
                //提示对话框
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_act_pay, null);
                final Dialog tipDialog = new Dialog(getActivity(), R.style.processDialog);
                tipDialog.setContentView(dialogView);
                //"我知道了"按钮
                TextView tv_confirm = (TextView) dialogView.findViewById(R.id.tv_confirm);
                tv_confirm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        tipDialog.dismiss();
                    }
                });
                Window dialogWindow = tipDialog.getWindow();
                WindowManager m = getActivity().getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
                dialogWindow.setAttributes(p);
                tipDialog.show();
                break;
//            case R.id.tv_apply:
//                Intent intent = new Intent(getActivity(), LoanConfirmActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
