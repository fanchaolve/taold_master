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
import com.bb.taold.activitiy.addBankCard.AddBankCardActivity;
import com.bb.taold.activitiy.loan.LoanConfirmActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.base.m.Frag_LoanModel;
import com.bb.taold.base.p.Frag_LoanPresenter;
import com.bb.taold.base.v.Frag_LoanContract;
import com.bb.taold.bean.Product;
import com.bb.taold.bean.ProductFee;
import com.bb.taold.bean.ProductInfo;
import com.bb.taold.bean.StagesInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by zhucheng'an on 2017/9/7.
 * Package Name com.bb.taold.fragment
 * Class Name LoanFragment
 */

public class LoanFragment extends BaseFragment
<Frag_LoanPresenter,Frag_LoanModel> implements Frag_LoanContract.View{
    @BindView(R.id.tv_loanAmount)
    TextView mTvLoanAmount;
    @BindView(R.id.tv_status_7)
    TextView mTvStatus7;
    @BindView(R.id.tv_status_14)
    TextView mTvStatus14;
    @BindView(R.id.tv_allPay)
    TextView mTvAllPay;
    @BindView(R.id.tv_needPay)
    TextView mTvNeedPay;
    @BindView(R.id.iv_question)
    ImageView mIvQuestion;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    //借款最高金额
    private int maxAmount = 0;
    //借款最低金额
    private int minAmount = 0;
    //接口返回接受
    private PostCallback postCallback;
    //保存最大金额和最小金额
    private Product mProduct = null;
    //分期数的信息
    private String stage7Id = "";
    private String stage14Id = "";
    //使用分期id保存
    private String userId = "";
    //各项费用保存
    private ProductFee mProductFee = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_loan;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                //判断哪个接口回调
                if(api.getT() instanceof ProductInfo){
                    //保存借款金额信息
                    ProductInfo mProductInfo = (ProductInfo) api.getT();
                    mProduct = mProductInfo.getProductInfo();
                    //设置最大金额和最小金额
                    maxAmount = Integer.parseInt(mProduct.getMaxAmt());
                    minAmount = Integer.parseInt(mProduct.getMinAmt());
                    //设置默认金额
                    mTvLoanAmount.setText(mProduct.getDefaultAmt());
                    //保存7天和14天对应的stagesId
                    ArrayList<StagesInfo> mList = mProductInfo.getStagesInfo();
                    for(int state=0;state<mList.size();state++){
                        if(mList.get(state).getStages().equals("7")){
                            //如果是7天
                            stage7Id = mList.get(state).getProductId();
                            continue;
                        }
                        if(mList.get(state).getStages().equals("14")){
                            //如果是7天
                            stage14Id = mList.get(state).getProductId();
                            continue;
                        }
                    }
                    //根据默认金额获取到账金额等信息
                    userId = stage7Id;
                    cacuAmount(userId,mProduct.getDefaultAmt());

                    return;
                }

                if(api.getT() instanceof ProductFee){
                    //获取各项费用
                    mProductFee = (ProductFee) api.getT();
                    mTvAllPay.setText(mProductFee.getTheActualToAccount());
                    mTvNeedPay.setText(mProductFee.getTotalMoney());
                    return;
                }
            }

            @Override
            public void failCallback() {

            }
        };

        //初始页面获取借款金额信息
        Call<Result_Api<ProductInfo>> call=service.productInfo("mini_loan");
        Callexts.need_sessionPost(call,postCallback);

    }

    /**
     * 在更换金额或更换期数后重新获取各项费用
     * @param productId
     * @param amount
     */
    public void cacuAmount(String productId,String amount){
        //根据默认金额获取到账金额等信息
        Call<Result_Api<ProductFee>> call=service.calProductFee(productId,amount);
        Callexts.need_sessionPost(call,postCallback);
    }

    //跳转到确认还款接口
    public void showConfirmActivity(){
        Bundle mBundle = new Bundle();
        mBundle.putString("loanAmount",mTvLoanAmount.getText().toString());
        if(userId.equals(stage7Id)){
            mBundle.putString("stage7Id",userId);
        }else{
            mBundle.putString("stage14Id",userId);
        }
        AppManager.getInstance().showActivity(LoanConfirmActivity.class,mBundle);
    }


    @OnClick({R.id.iv_delete, R.id.iv_add, R.id.iv_question,R.id.tv_confirm,R.id.tv_status_7,R.id.tv_status_14})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm://申请之前
                mPresenter.memberInfo();


                break;

            case R.id.iv_delete:
                //获取当前金额
                int currentAmountDelete = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否小于最小金额
                if(currentAmountDelete == minAmount){
                    mTvLoanAmount.setText(minAmount + "");
                    return;
                }
                if (currentAmountDelete - 100 < minAmount) {
                    mTvLoanAmount.setText(minAmount + "");
                }else{
                    mTvLoanAmount.setText((currentAmountDelete - 100) + "");
                }
                //重新获取接口计算各项费用
                cacuAmount(userId,mTvLoanAmount.getText().toString());
                break;
            case R.id.iv_add:
                //获取当前金额
                int currentAmount = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否大于最大金额
                if(currentAmount == maxAmount){
                    mTvLoanAmount.setText(maxAmount + "");
                    return;
                }
                if ((currentAmount + 100) > maxAmount) {
                    mTvLoanAmount.setText(maxAmount + "");
                }else{
                    mTvLoanAmount.setText((currentAmount + 100) + "");
                }
                //重新获取接口计算各项费用
                cacuAmount(userId,mTvLoanAmount.getText().toString());
                break;
            case R.id.iv_question:
                //提示对话框
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_act_pay, null);
                final Dialog tipDialog = new Dialog(getActivity(), R.style.processDialog);
                tipDialog.setContentView(dialogView);

                //实际到账金额
                TextView tv_theActualToAccount = (TextView)dialogView.findViewById(R.id.tv_theActualToAccount);
                tv_theActualToAccount.setText("+"+mProductFee.getTheActualToAccount()+"元");
                //利息费
                TextView tv_interestRates = (TextView)dialogView.findViewById(R.id.tv_interestRates);
                tv_interestRates.setText("+"+mProductFee.getInterestRates()+"元");
                //额度审核费
                TextView tv_creditAuditRates = (TextView)dialogView.findViewById(R.id.tv_creditAuditRates);
                tv_creditAuditRates.setText("+"+mProductFee.getCreditAuditRates()+"元");
                //征信查询费
                TextView tv_creditInspectRates = (TextView)dialogView.findViewById(R.id.tv_creditInspectRates);
                tv_creditInspectRates.setText("+"+mProductFee.getCreditInspectRates()+"元");
                //贷后管理费
                TextView tv_manageRates = (TextView)dialogView.findViewById(R.id.tv_manageRates);
                tv_manageRates.setText("+"+mProductFee.getManageRates()+"元");
                //介绍费
                TextView tv_introduceRates = (TextView)dialogView.findViewById(R.id.tv_introduceRates);
                tv_introduceRates.setText("+"+mProductFee.getIntroduceRates()+"元");
                //合计
                TextView tv_totalMoney = (TextView)dialogView.findViewById(R.id.tv_totalMoney);
                tv_totalMoney.setText("+"+mProductFee.getTotalMoney()+"元");

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
            case R.id.tv_status_7:
                //点击"借7天"按钮
                mTvStatus7.setTextColor(getResources().getColor(R.color.color_price));
                mTvStatus7.setBackground(getResources().getDrawable(R.drawable.bg_loan_text_2));
                mTvStatus14.setTextColor(getResources().getColor(R.color.font_normal));
                mTvStatus14.setBackground(getResources().getDrawable(R.drawable.bg_loan_text));
                userId = stage7Id;
                //重新获取接口计算各项费用
                cacuAmount(userId,mTvLoanAmount.getText().toString());
                break;
            case R.id.tv_status_14:
                //点击"借14天"按钮
                mTvStatus14.setTextColor(getResources().getColor(R.color.color_price));
                mTvStatus14.setBackground(getResources().getDrawable(R.drawable.bg_loan_text_2));
                mTvStatus7.setTextColor(getResources().getColor(R.color.font_normal));
                mTvStatus7.setBackground(getResources().getDrawable(R.drawable.bg_loan_text));
                userId = stage14Id;
                //重新获取接口计算各项费用
                cacuAmount(userId,mTvLoanAmount.getText().toString());
                break;
        }
    }

    @Override
    public String getTotalAmount() {
        return mTvLoanAmount.getText().toString();
    }

    @Override
    public String getCurrentId() {
        return userId;
    }

    @Override
    public String get7RateId() {
        return stage7Id;
    }
}
