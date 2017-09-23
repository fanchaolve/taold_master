package com.bb.taold.activitiy.addBankCard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.loan.LoanConfirmActivity;
import com.bb.taold.api.ApiServiveImpl;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.BandCardResult;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.LoanBundle;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.events.AddCard;
import com.bb.taold.lianlian.utils.BaseHelper;
import com.bb.taold.lianlian.utils.LLPayConstants;
import com.bb.taold.lianlian.utils.MobileSecurePayer;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CacheUtils;
import com.bb.taold.utils.CardNumScanUtil;
import com.bb.taold.utils.Constants;
import com.idcard.CardInfo;
import com.idcard.TFieldID;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

import static com.bb.taold.R.id.tv_acctName;

/**
 * Created by zhucheng'an on 2017/9/11.
 * 添加银行卡页面
 */

public class AddBankCardFinalActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_acctUser)
    TextView mEtAcctUser;
    /**
     * 银行卡号
     */
    @BindView(R.id.et_acctNo)
    EditText mEtAcctNo;
    /**
     * 银行名称
     */
    @BindView(tv_acctName)
    TextView mEtAcctName;
    @BindView(R.id.et_acctPhone)
    EditText mEtAcctPhone;

    private int from_Act = 0;//0绑卡,1添加
    private UserInfo info;//
    private CardCheck mCardCheck;
    private CardInfo cardInfo;
    private String mFrom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addcardfinal;
    }

    @Override
    public void initView() {
        //设置返回按钮为可见
        mBtnBack.setVisibility(View.VISIBLE);

    }

    @Override
    public void initListener() {
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            mFrom = bundle.getString(Constants.ADD_CARD_FROM);
        }
        mTvTitle.setText("绑定银行卡");
        getUserInfo();
    }


    /**
     * 绑卡
     */
    private void createNewBankCard(String llAgreeNo, final Bundle mBundle) {
        Call<Result_Api> call = service.createNewBankCard(mCardCheck.getBankCode(),
                info.getRealName(), mEtAcctNo.getText().toString().replace(" ", ""),
                info.getIdCard(), mCardCheck.getBankName(),
                mEtAcctPhone.getText().toString(), "0", llAgreeNo);
        Callexts.need_sessionPost(call, new PostCallback<AddBankCardFinalActivity>(this) {
            @Override public void successCallback(Result_Api api) {
                showMsg("绑定成功");
                if (mFrom.equals(Constants.FROM_AUTU)) {
                    AppManager.getInstance().showActivity(LoanConfirmActivity.class, mBundle);
                    CacheUtils.saveDataToDiskLruCache(Constants.TO_CONFIRM_ACTIVIY, null);
                }
                EventBus.getDefault().post(new AddCard());
                finish();
            }

            @Override public void failCallback() {

            }
        });
    }

    private void getLLToken() {
        Call<Result_Api<BandCardResult>> call = new ApiServiveImpl().getLLToken(mEtAcctNo.getText().toString(), mEtAcctPhone.getText().toString());
        Callexts.need_sessionPost(call, new PostCallback<AddBankCardFinalActivity>(this) {
            @Override public void successCallback(Result_Api api) {
                if (api.getT() instanceof BandCardResult) {
                    BandCardResult bandCardResult = (BandCardResult) api.getT();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("no_order", bandCardResult.getNo_order());
                        jsonObject.put("oid_partner", bandCardResult.getOid_partner());
                        jsonObject.put("user_id", bandCardResult.getUser_id());
                        jsonObject.put("token", bandCardResult.getToken());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MobileSecurePayer payer = new MobileSecurePayer();
                    payer.setCallbackHandler(mHandler, LLPayConstants.RQF_SIGN);
                    payer.setTestMode(false);
                    boolean bRet = payer.doTokenSign(jsonObject, AddBankCardFinalActivity.this);
                }
            }

            @Override public void failCallback() {

            }
        });
    }

    private void getCardInfo(String ordNo) {
        Call<Result_Api<CardCheck>> call = service.supportCard(ordNo);
        Callexts.need_sessionPost(call, new PostCallback() {
            @Override public void successCallback(Result_Api api) {
                if (api.getT() instanceof CardCheck) {
                    mCardCheck = (CardCheck) api.getT();
                    if (!TextUtils.isEmpty(mCardCheck.getBankName())) {
                        mEtAcctName.setText(mCardCheck.getBankName());
                    }

                }
            }

            @Override public void failCallback() {

            }
        });
    }

    private void getUserInfo() {
        Call<Result_Api<UserInfo>> call = service.user_info();
        Callexts.need_sessionPost(call, new PostCallback<BaseActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (mContext == null)
                    return;

                if (api.getT() instanceof UserInfo) {
                    info = (UserInfo) api.getT();
                    if (info == null)
                        return;
                    mEtAcctUser.setText(info.getRealName());
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }


//    private void updateAgreeNo(String llAgreeNo, String cardNo, final Bundle bundle) {
//        Call<Result_Api<String>> call = new ApiServiveImpl().updateAgreeNo(llAgreeNo, cardNo);
//        Callexts.need_sessionPost(call, new PostCallback<AddBankCardFinalActivity>(this) {
//            @Override public void successCallback(Result_Api api) {
//                showMsg("绑定成功");
//                if (mFrom.equals(Constants.FROM_AUTU)) {
//                    AppManager.getInstance().showActivity(LoanConfirmActivity.class, bundle);
//                    CacheUtils.saveDataToDiskLruCache(Constants.TO_CONFIRM_ACTIVIY, null);
//                }
//                EventBus.getDefault().post(new AddCard());
//                finish();
//            }
//
//            @Override public void failCallback() {
//
//            }
//        });
//    }


    private Handler mHandler = createHandler();

    private Handler createHandler() {
        return new Handler() {
            public void handleMessage(Message msg) {
                String strRet = (String) msg.obj;
                switch (msg.what) {
                    case LLPayConstants.RQF_PAY:
                    case LLPayConstants.RQF_SIGN: {
                        JSONObject objContent = BaseHelper.string2JSON(strRet);
                        String retCode = objContent.optString("ret_code");
                        String retMsg = objContent.optString("ret_msg");
                        String no_agree = objContent.optString("no_agree");
                        Log.e("lllllll", objContent.toString());

                        // 成功
                        if (LLPayConstants.RET_CODE_SUCCESS.equals(retCode)) {
                            LoanBundle loanBundle = (LoanBundle) CacheUtils.getDataCache(Constants.TO_CONFIRM_ACTIVIY);
                            Bundle mBundle = new Bundle();
                            mBundle.putSerializable("key", loanBundle);
                            createNewBankCard(no_agree, mBundle);
//                            updateAgreeNo(no_agree, mEtAcctNo.getText().toString().replace(" ", ""), mBundle);

//                            BaseHelper.showDialog(AddBankCardFinalActivity.this, "提示",
//                                    "支付成功，交易状态码：" + retCode + " 返回报文:" + strRet,
//                                    android.R.drawable.ic_dialog_alert);

                        } else {
                            // TODO 失败
                            showMsg("绑定失败");
//                            BaseHelper.showDialog(AddBankCardFinalActivity.this, "错误提示", retMsg
//                                            + "，交易状态码:" + retCode + " 返回报文:" + strRet,
//                                    android.R.drawable.ic_dialog_alert);
                        }
                    }
                    break;
                }
                super.handleMessage(msg);
            }
        };

    }

    @OnClick({R.id.btn_back, R.id.iv_tip_name, R.id.iv_tip_phone, R.id.tv_next, R.id.iv_scan_card, R.id.tv_acctName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_tip_name:
                break;
            case R.id.iv_tip_phone:
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(mEtAcctPhone.getText().toString())) {
                    showTip("请填写预留手机号");
                    break;
                }
                if (mCardCheck == null || info == null) {
                    showTip("未获取卡片或者个人信息");
                    return;
                }
                getLLToken();
                break;
            case R.id.iv_scan_card:
                //添加银行卡页面
                CardNumScanUtil.getINSTANCE().doScan();
                break;
            case R.id.tv_acctName:
                String cardNum = mEtAcctNo.getText().toString();
                if (cardNum.length() > 0) {
                    getCardInfo(mEtAcctNo.getText().toString());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == CardNumScanUtil.RESULT_GET_OK) {
                // 处理银行卡扫描结果（在界面上显示）
                if (data == null) {
                    return;
                }
                cardInfo = (CardInfo) data.getSerializableExtra("cardinfo");
                String cardNo = cardInfo.getFieldString(TFieldID.TBANK_NUM);
                if (!TextUtils.isEmpty(cardNo)) {
                    cardNo = cardNo.replace(" ", "").trim();
                    mEtAcctNo.setText(cardNo);
                    getCardInfo(cardNo);
                }
            }
        }
    }

}
