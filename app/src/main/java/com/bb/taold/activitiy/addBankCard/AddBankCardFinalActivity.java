package com.bb.taold.activitiy.addBankCard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.bb.taold.bean.UserInfo;
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
import com.turui.bank.ocr.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
    @BindView(R.id.tv_acctName)
    TextView mEtAcctName;
    @BindView(R.id.et_acctPhone)
    EditText mEtAcctPhone;

    private int from_Act = 0;//0绑卡,1添加


    private UserInfo info;//

    private CardCheck mCardCheck;
    private PostCallback postCallback = new PostCallback<BaseActivity>(this) {
        @Override
        public void successCallback(Result_Api api) {
            if (mContext == null)
                return;

            if (api.getT() instanceof UserInfo) {
                info = (UserInfo) api.getT();
                if (info == null)
                    return;
                mEtAcctUser.setText(info.getRealName());
            } else if (api.getT() instanceof BandCardResult) {
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

                Log.i("fancl", ((BandCardResult) api.getT()).getToken());
                MobileSecurePayer payer = new MobileSecurePayer();
//                            payer.setCAPTCHA_Switch(true);
                payer.setCallbackHandler(mHandler, LLPayConstants.RQF_SIGN);
                payer.setTestMode(false);
                boolean bRet = payer.doTokenSign(jsonObject, AddBankCardFinalActivity.this);
//                finish();
                AppManager.getInstance().finishParticularActivity(AddBankCardActivity.class);
            }
        }

        @Override
        public void failCallback() {

        }
    };

    private PostCallback checkCardPost = new PostCallback() {
        @Override public void successCallback(Result_Api api) {
            if (api.getT() instanceof CardCheck) {
                mCardCheck = (CardCheck) api.getT();
                if(!TextUtils.isEmpty(mCardCheck.getBankName())){
                    mEtAcctName.setText(mCardCheck.getBankName());
                }

            }
        }

        @Override public void failCallback() {

        }
    };
    private CardInfo cardInfo;

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
        mEtAcctNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override public void afterTextChanged(Editable s) {
                int len = s.toString().length();
                //银行卡号长度
                if(len>=16&&len<=19){
                    getCardState(s.toString());
                }
            }
        });
    }

    @Override
    public void initdata() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle bundle = intent.getExtras();
//            if (bundle != null && bundle.containsKey("card")) {
//                cardCheck = (CardCheck) bundle.getSerializable("card");
//
//            }
//
//            if (bundle != null && bundle.containsKey("from_Act")) {
//                from_Act = bundle.getInt("from_Act");
//            }
//        }
//
//        if (from_Act == 0) {
            mTvTitle.setText("绑定银行卡");
//        } else if (from_Act == 1) {
//            mTvTitle.setText("添加银行卡");
//        }
//
//        if (cardCheck == null)
//            return;
//        mEtAcctNo.setText(cardCheck.getCardNo().replaceAll("\\d{4}(?!$)", "$0 "));
//        mEtAcctName.setText(cardCheck.getBankName());
        Call<Result_Api<UserInfo>> call = service.user_info();
        Callexts.need_sessionPost(call, postCallback);


    }


    @OnClick({R.id.btn_back, R.id.iv_tip_name, R.id.iv_tip_phone, R.id.tv_next,R.id.iv_scan_card})
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

                Call<Result_Api<BandCardResult>> call = service.createNewBankCard(mCardCheck.getBankCode(),
                        info.getRealName(), mEtAcctNo.getText().toString().replace(" ", ""),
                        info.getIdCard(), mCardCheck.getBankName(),
                        mEtAcctPhone.getText().toString(), "0");
                Callexts.need_sessionPost(call, postCallback);
                break;
            case R.id.iv_scan_card:
                //添加银行卡页面
                CardNumScanUtil.getINSTANCE().doScan();
                break;
        }
    }

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

                            //关闭所有认证页面事件
                            Map<String, String> map = (Map<String, String>) CacheUtils.getDataCache(Constants.TO_CONFIRM_ACTIVIY);
                            Bundle mBundle = new Bundle();
                            mBundle.putString("loanAmount", map.get("loanAmount"));
                            if (map.containsKey("stage7Id")) {
                                mBundle.putString("stage7Id", map.get("stage7Id"));
                            } else {
                                mBundle.putString("stage4Id", map.get("stage4Id"));
                            }
                            updateAgreeNo(no_agree, mEtAcctNo.getText().toString().replace(" ", ""), mBundle);

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

    private void updateAgreeNo(String llAgreeNo, String cardNo, final Bundle bundle) {
//        2017092133093907
        showMsg("绑定成功");
        Call<Result_Api<String>> call = new ApiServiveImpl().updateAgreeNo(llAgreeNo, cardNo);
        Callexts.need_sessionPost(call, new PostCallback<AddBankCardFinalActivity>(this) {
            @Override public void successCallback(Result_Api api) {
                EventBus.getDefault().post(Constants.AFTER_AUTH_CLOSE);
                AppManager.getInstance().showActivity(LoanConfirmActivity.class, bundle);
                CacheUtils.saveDataToDiskLruCache(Constants.TO_CONFIRM_ACTIVIY, null);
                finish();
            }

            @Override public void failCallback() {

            }
        });
    }

    private void getCardState(String ordNo) {
        Call<Result_Api<CardCheck>> call=service.supportCard(ordNo);
        Callexts.need_sessionPost(call,checkCardPost);
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
                if(!TextUtils.isEmpty(cardNo)){
                    cardNo = cardNo.replace(" ", "").trim();
                    mEtAcctNo.setText(cardNo);
                    getCardState(cardNo);
                }
                //银行卡照片
                Bitmap bitmap = CaptureActivity.TakeBitmap;
//                if (bitmap == null)
//                    return;
//                iv_take.setImageBitmap(bitmap);
            }
        }
    }

}
