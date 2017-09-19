package com.bb.taold.activitiy.repay;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.BillItemDetail;
import com.bb.taold.bean.BillItemInfo;
import com.bb.taold.bean.PayParams;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.EBJPayUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
    @BindView(R.id.tv_loanAmount)
    TextView mTvLoanAmount;
    @BindView(R.id.tv_principal)
    TextView mTvPrincipal;
    @BindView(R.id.tv_loanInterestCost)
    TextView mTvLoanInterestCost;
    @BindView(R.id.tv_manageCost)
    TextView mTvManageCost;
    @BindView(R.id.tv_dueAmount)
    TextView mTvDueAmount;
    @BindView(R.id.tv_abateAmt)
    TextView mTvAbateAmt;
    @BindView(R.id.tv_has_repay_amt)
    TextView mTvHasRepayAmt;

    //判断该笔账单属于待还款，还款成功，已逾期
    private String REPAY_PROCESSING = "0";//待还款
    private String REPAY_SUCCESS = "1";//还款成功
    private String REPAY_OVERDUE = "2";//已逾期

    //接口返回接受
    private PostCallback postCallback;
    //保存billItemId
    private String billItemId = "";
    private PayParams mPayParams;
    private BillItemDetail mDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_repayinfo;
    }

    @Override
    public void initView() {
        //设置标题
        mTvTitle.setText("立即还款");
        mBtnBack.setVisibility(View.VISIBLE);
        //设置状态
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle mBunle = getIntent().getExtras();
            //保存billItemId
            billItemId = mBunle.getString("billItemId");
            if (mBunle.getString("bill_status").equals(REPAY_PROCESSING)) {
                //待还款
                //立即还款按钮设置可见
                mTvConfirm.setVisibility(View.VISIBLE);
                //成功页面设置不可见
                mLlSuccess.setVisibility(View.GONE);
                //已还清设置不可见
                mLlAllpay.setVisibility(View.GONE);
            } else if (mBunle.getString("bill_status").equals(REPAY_SUCCESS)) {
                //还款成功
                //立即还款按钮设置不可见
                mTvConfirm.setVisibility(View.GONE);
                //成功页面设置可见
                mLlSuccess.setVisibility(View.VISIBLE);
                //已还清设置可见
                mLlAllpay.setVisibility(View.VISIBLE);
                mTvTitle.setText("已还款");
            } else if (mBunle.getString("bill_status").equals(REPAY_OVERDUE)) {
                //逾期
                //立即还款按钮设置可见
                mTvConfirm.setVisibility(View.VISIBLE);
                //成功页面设置不可见
                mLlSuccess.setVisibility(View.GONE);
                //已还清设置不可见
                mLlAllpay.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        postCallback = new PostCallback<BaseActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof BillItemInfo) {
                    BillItemInfo info = (BillItemInfo)api.getT();
                    mDetail = info.getBillInfo();
                    //设置金额数据
                    mTvLoanAmount.setText(mDetail.getBillAmount());
                    mTvPrincipal.setText(mDetail.getPrincipal());
                    mTvLoanInterestCost.setText(mDetail.getLoanInterestCost());
                    mTvManageCost.setText(mDetail.getManageCost());
                    //需要判断是否逾期来确定是否显示逾期金额
                    mTvDueAmount.setText(mDetail.getDueAmount());
                    mTvAbateAmt.setText(mDetail.getAbateAmt());
                    getPayParams(mDetail.getBillId(),mDetail.getBillAmount(), Constants.PAY_CHANNEL_ALIPAY,Constants.PLATFORM);
                    return;
                }else if(api.getT() instanceof PayParams){
                    mPayParams = (PayParams) api.getT();
                }
            }

            @Override
            public void failCallback() {

            }
        };
        //获取账单明细
        getBillItemInfo(billItemId);

    }

    /**
     * 根据billItemId获取账单明细
     *
     * @param billItemId
     */
    private void getBillItemInfo(String billItemId) {
        Call<Result_Api<BillItemInfo>> call = service.fundDetail(billItemId);
        Callexts.need_sessionPost(call, postCallback);
    }

    public void createDialog() {
        /**
         * 确定还款时弹出选择方式对话框
         */
        final Dialog dialog = new Dialog(RepayInfoActivity.this, R.style.processDialog);
        View view = RepayInfoActivity.this.getLayoutInflater().inflate(R.layout.dialog_payway, null);
        dialog.setContentView(view);

        //支付宝还款
        final LinearLayout mLlAlipay = (LinearLayout) view.findViewById(R.id.ll_alipay);
        mLlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //支付宝还款监听事件
                showTip("支付宝还款");
                dialog.dismiss();
                if(mPayParams!=null){
                    EBJPayUtil ebjPayUtil = new EBJPayUtil(mContext, mPayParams.getMerchantOutOrderNo(), mPayParams.getMerid(),
                            mPayParams.getNoncestr(), mPayParams.getOrderMoney(), mPayParams.getOrderTime());
                    ebjPayUtil.startPay();
                }

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
    //请求支付参数
    public void getPayParams(String billItemId,String amount,String payChannel,String oidChnl){
        Call<Result_Api<PayParams>> result_apiCall = service.loan_repay(billItemId, amount, payChannel, oidChnl);
        Callexts.Unneed_sessionPost(result_apiCall,postCallback);
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        createDialog();
    }

}
