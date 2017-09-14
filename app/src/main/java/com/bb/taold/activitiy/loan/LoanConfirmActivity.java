package com.bb.taold.activitiy.loan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.Product;
import com.bb.taold.bean.ProductFee;
import com.bb.taold.bean.ProductInfo;
import com.bb.taold.bean.StagesInfo;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.listener.Callexts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
    @BindView(R.id.tv_interestRates)
    TextView mTvInterestRates;
    @BindView(R.id.tv_creditAuditRates)
    TextView mTvCreditAuditRates;
    @BindView(R.id.tv_creditInspectRates)
    TextView mTvCreditInspectRates;
    @BindView(R.id.tv_manageRates)
    TextView mTvManageRates;
    @BindView(R.id.tv_introduceRates)
    TextView mTvIntroduceRates;
    @BindView(R.id.tv_totalMoney)
    TextView mTvTotalMoney;
    @BindView(R.id.iv_bankicon)
    ImageView mIvBankicon;
    @BindView(R.id.tv_bankname)
    TextView mTvBankname;
    @BindView(R.id.tv_banktype)
    TextView mTvBanktype;
    @BindView(R.id.tv_cardno)
    TextView mTvCardno;
    @BindView(R.id.tv_theActualToAccount)
    TextView mTvTheActualToAccount;
    @BindView(R.id.iv_btn1)
    ImageView mIvBtn1;
    @BindView(R.id.iv_btn2)
    ImageView mIvBtn2;

    //借款最大金额
    private int maxAmount = 20000;
    //借款最小金额
    private int minAmount = 1000;
    //接口返回接受
    private PostCallback postCallback;
    //保存最大金额和最小金额
    private Product mProduct = null;
    //当前分期数id
    private String userId = "";
    //各项费用保存
    private ProductFee mProductFee = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

        //获取借款页面传递的值
        if(getIntent()!=null){
            Bundle mBundle = getIntent().getExtras();
            mTvLoanAmount.setText(mBundle.getString("loanAmount"));
            if(mBundle.containsKey("stage7Id")){
                mTvLoanDays.setText("7天");
                userId = mBundle.getString("stage7Id");
            }else{
                mTvLoanDays.setText("14天");
                userId = mBundle.getString("stage14Id");
            }
        }

        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                //判断哪个接口回调
                if(getFlag() == 0){
                    //保存借款金额信息
                    ProductInfo mProductInfo = (ProductInfo) api.getT();
                    mProduct = mProductInfo.getProductInfo();
                    //设置最大金额和最小金额
                    maxAmount = Integer.parseInt(mProduct.getMaxAmt());
                    minAmount = Integer.parseInt(mProduct.getMinAmt());
                    cacuAmount(userId,mTvLoanAmount.getText().toString());

                    return;
                }

                if(getFlag() == 1){
                    //获取各项费用
                    mProductFee = (ProductFee) api.getT();
                    //实际到账金额
                    mTvTheActualToAccount.setText(mProductFee.getTheActualToAccount()+"元");
                    //利息费
                    mTvInterestRates.setText(mProductFee.getInterestRates()+"元");
                    //额度审核费
                    mTvCreditAuditRates.setText(mProductFee.getCreditAuditRates()+"元");
                    //征信查询费
                    mTvCreditInspectRates.setText(mProductFee.getCreditInspectRates()+"元");
                    //贷后管理费
                    mTvManageRates.setText(mProductFee.getManageRates()+"元");
                    //介绍费
                    mTvIntroduceRates.setText(mProductFee.getIntroduceRates()+"元");
                    //总计
                    mTvTotalMoney.setText(mProductFee.getTotalMoney()+"元");
                    return;
                }

            }

            @Override
            public void failCallback() {

            }
        };

        //初始页面获取借款金额信息
        Call call = service.productInfo("mini_loan");
        postCallback.setFlag(0);
        call.enqueue(postCallback);

    }

    /**
     * 在更换金额或更换期数后重新获取各项费用
     * @param productId
     * @param amount
     */
    public void cacuAmount(String productId,String amount){
        //根据默认金额获取到账金额等信息
        Call call = service.calProductFee(productId,amount);
        postCallback.setFlag(1);
        call.enqueue(postCallback);
    }

    @OnClick({R.id.btn_back, R.id.iv_add, R.id.tv_loanAmount, R.id.iv_delete, R.id.tv_confirm,R.id.iv_btn1,R.id.iv_btn2})
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
                cacuAmount(userId,mTvLoanAmount.getText().toString());
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
                cacuAmount(userId,mTvLoanAmount.getText().toString());
                break;
            case R.id.tv_confirm:

                break;
            case R.id.iv_btn1:
                mIvBtn1.setImageResource(R.drawable.square_sel);
                break;
            case R.id.iv_btn2:
                break;
        }
    }
}
