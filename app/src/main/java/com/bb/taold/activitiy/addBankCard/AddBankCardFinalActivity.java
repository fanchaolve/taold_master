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
import com.bb.taold.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by zhucheng'an on 2017/9/11.
 * Package Name com.bb.taold.activitiy.addBankCard
 * Class Name AddBankCardFinalActivity
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
    @BindView(R.id.tv_acctNo)
    EditText mEtAcctNo;
    /**
     * 银行名称
     */
    @BindView(R.id.tv_acctName)
    TextView mEtAcctName;
    @BindView(R.id.et_acctPhone)
    EditText mEtAcctPhone;

    private int from_Act = 0;//0绑卡,1添加

    private CardCheck cardCheck;//判断是否为有效卡

    private UserInfo info;//

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
            if (bundle != null && bundle.containsKey("card")) {
                cardCheck = (CardCheck) bundle.getSerializable("card");

            }

            if (bundle != null && bundle.containsKey("from_Act")) {
                from_Act = bundle.getInt("from_Act");
            }
        }

        if (from_Act == 0) {
            mTvTitle.setText("绑定银行卡");
        } else if (from_Act == 1) {
            mTvTitle.setText("添加银行卡");
        }

        if (cardCheck == null)
            return;
        mEtAcctNo.setText(cardCheck.getCardNo().replaceAll("\\d{4}(?!$)", "$0 "));
        mEtAcctName.setText(cardCheck.getBankName());
        Call<Result_Api<UserInfo>> call = service.user_info();
        Callexts.need_sessionPost(call, postCallback);


    }


    @OnClick({R.id.btn_back, R.id.iv_tip_name, R.id.iv_tip_phone, R.id.tv_next})
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
                if (cardCheck == null && info == null) {
                    showTip("未获取卡片或者个人信息");
                    return;
                }

                Call<Result_Api<BandCardResult>> call = service.createNewBankCard(cardCheck.getBankCode(),
                        info.getRealName(), mEtAcctNo.getText().toString().replace(" ", ""),
                        info.getIdCard(), cardCheck.getBankName(),
                        mEtAcctPhone.getText().toString(), "0");
                Callexts.need_sessionPost(call, postCallback);
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

                        // 成功
                        if (LLPayConstants.RET_CODE_SUCCESS.equals(retCode)) {
                            showMsg("绑定成功");
                            //关闭所有认证页面事件
                            EventBus.getDefault().post(Constants.AFTER_AUTH_CLOSE);
                            Map<String,String> map = (Map<String, String>) CacheUtils.getDataCache(Constants.TO_CONFIRM_ACTIVIY);
                            Bundle mBundle = new Bundle();
                            mBundle.putString("loanAmount",map.get("loanAmount"));
                            if (mBundle.containsKey("stage7Id")) {
                                mBundle.putString("stage7Id",map.get("stage7Id"));
                            }else{
                                mBundle.putString("stage4Id",map.get("stage4Id"));
                            }

                            AppManager.getInstance().showActivity(LoanConfirmActivity.class,mBundle);
                            CacheUtils.saveDataToDiskLruCache(Constants.TO_CONFIRM_ACTIVIY,null);
                            finish();
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
}
