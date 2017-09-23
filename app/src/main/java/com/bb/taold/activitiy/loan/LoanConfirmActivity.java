package com.bb.taold.activitiy.loan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.cardList.CardListActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardInfo;
import com.bb.taold.bean.Cardinfos;
import com.bb.taold.bean.LoanBundle;
import com.bb.taold.bean.LoanInfo;
import com.bb.taold.bean.Product;
import com.bb.taold.bean.ProductFee;
import com.bb.taold.bean.ProductInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.DeviceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private String stageId = "";
    //各项费用保存
    private ProductFee mProductFee = null;
    //保存主卡信息
    private CardInfo mCardInfo = null;
    private LoanBundle loanBundle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mIvBtn2.setSelected(true);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        EventBus.getDefault().register(mContext);
        //获取借款页面传递的值
        if (getIntent() != null) {
            Bundle mBundle = getIntent().getExtras();
            loanBundle = (LoanBundle) mBundle.getSerializable("key");
            mTvLoanAmount.setText(loanBundle.getLoanAmount());
            mTvLoanDays.setText(loanBundle.getLoanType());
            stageId = loanBundle.getStageId();
        }

        postCallback = new PostCallback<LoanConfirmActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                //判断哪个接口回调
                if (api.getT() instanceof ProductInfo) {
                    //保存借款金额信息
                    ProductInfo mProductInfo = (ProductInfo) api.getT();
                    mProduct = mProductInfo.getProductInfo();
                    //设置最大金额和最小金额
                    maxAmount = Integer.parseInt(mProduct.getMaxAmt());
                    minAmount = Integer.parseInt(mProduct.getMinAmt());
                    cacuAmount(stageId, mTvLoanAmount.getText().toString());

                    return;
                }

                if (api.getT() instanceof ProductFee) {
                    //获取各项费用
                    mProductFee = (ProductFee) api.getT();
                    //实际到账金额
                    mTvTheActualToAccount.setText(mProductFee.getTheActualToAccount() + "元");
                    //利息费
                    mTvInterestRates.setText(mProductFee.getInterestRates() + "元");
                    //额度审核费
                    mTvCreditAuditRates.setText(mProductFee.getCreditAuditRates() + "元");
                    //征信查询费
                    mTvCreditInspectRates.setText(mProductFee.getCreditInspectRates() + "元");
                    //贷后管理费
                    mTvManageRates.setText(mProductFee.getManageRates() + "元");
                    //介绍费
                    mTvIntroduceRates.setText(mProductFee.getIntroduceRates() + "元");
                    //总计
                    mTvTotalMoney.setText(mProductFee.getTotalMoney() + "元");
                    return;
                }
                //获取银行卡列表
                if (api.getT() instanceof Cardinfos) {
                    ArrayList<CardInfo> cards = (ArrayList<CardInfo>) api.getT();
                    if (cards != null && cards.size() > 0) {
                        mCardInfo = cards.get(0);
                        if (mCardInfo != null) {
                            setCardInfoView(mCardInfo);
                        }
                    }

                }

                if (api.getT() instanceof LoanInfo) {
                    AppManager.getInstance().showActivity(ApplySuccessActivity.class, null);
                }

            }

            @Override
            public void failCallback() {

            }
        };

        //初始页面获取借款金额信息
        Call<Result_Api<ProductInfo>> call = service.productInfo("mini_loan");
        Callexts.need_sessionPost(call, postCallback);

        Call<Result_Api<Cardinfos>> callCard = service.bankList("10");
        Callexts.need_sessionPost(callCard, postCallback);
    }

    /**
     * 在更换金额或更换期数后重新获取各项费用
     *
     * @param productId
     * @param amount
     */
    public void cacuAmount(String productId, String amount) {
        //根据默认金额获取到账金额等信息
        Call<Result_Api<ProductFee>> call = service.calProductFee(productId, amount);
        Callexts.need_sessionPost(call, postCallback);
    }

    @OnClick({R.id.btn_back, R.id.iv_add, R.id.tv_loanAmount, R.id.iv_delete, R.id.tv_confirm, R.id.agree_layout1, R.id.agree_layout2,
            R.id.tv_changeCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_add:
                //获取当前金额
                int currentAmount = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否大于最大金额
                if (currentAmount == maxAmount) {
                    mTvLoanAmount.setText(maxAmount + "");
                    return;
                }
                if ((currentAmount + 100) > maxAmount) {
                    mTvLoanAmount.setText(maxAmount + "");
                } else {
                    mTvLoanAmount.setText((currentAmount + 100) + "");
                }
                //刷新所有的费用
                cacuAmount(stageId, mTvLoanAmount.getText().toString());
                break;
            case R.id.iv_delete:
                //获取当前金额
                int currentAmountDelete = Integer.parseInt(mTvLoanAmount.getText().toString());
                //判断金额是否小于最小金额
                if (currentAmountDelete == minAmount) {
                    mTvLoanAmount.setText(minAmount + "");
                    return;
                }
                if (currentAmountDelete - 100 < minAmount) {
                    mTvLoanAmount.setText(minAmount + "");
                } else {
                    mTvLoanAmount.setText((currentAmountDelete - 100) + "");
                }
                //刷新所有的费用
                cacuAmount(stageId, mTvLoanAmount.getText().toString());
                break;
            case R.id.tv_confirm:
                if (!mIvBtn1.isSelected() || !mIvBtn2.isSelected()) {
                    showTip("请阅读协议");
                    return;
                }
                //调用小额贷款接口
                Call<Result_Api<LoanInfo>> call = service.applyMiniLoan(mCardInfo.getId(), mTvLoanAmount.getText().toString(),
                        mTvLoanDays.getText().toString().replace("天", ""), MyApplication.longitude + "-" + MyApplication.latitude,
                        DeviceUtils.getDeviceIdentification(this), "1", "1", "1", "1");
                Callexts.need_sessionPost(call, postCallback);
                break;
            case R.id.agree_layout1:
                if (mIvBtn1.isSelected())
                    mIvBtn1.setImageResource(R.drawable.square_nor);
                else
                    mIvBtn1.setImageResource(R.drawable.square_sel);
                mIvBtn1.setSelected(!mIvBtn1.isSelected());
                break;
            case R.id.agree_layout2:
                if (mIvBtn2.isSelected())
                    mIvBtn2.setImageResource(R.drawable.square_nor);
                else
                    mIvBtn2.setImageResource(R.drawable.square_sel);
                mIvBtn2.setSelected(!mIvBtn2.isSelected());
                break;
            case R.id.tv_changeCard:
                AppManager.getInstance().showActivity(CardListActivity.class, null);
                break;
        }
    }

    public void setCardInfoView(CardInfo cardInfo) {
        //银行名称
        mTvBankname.setText(mCardInfo.getCardName());
        //卡片类型
        mTvBanktype.setText("借记卡");
        //银行卡号
        String cardNo = mCardInfo.getCardno();
        if (cardNo.length() > 4) {
            cardNo = cardNo.substring(cardNo.length() - 4, cardNo.length());
        }
        mTvCardno.setText("**** **** **** " + cardNo);
    }

    @Subscribe
    public void onEventMainThread(CardInfo info) {
        if (info != null) {
            setCardInfoView(info);
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }
}
