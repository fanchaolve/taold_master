/**  
   * Copyright (C) 2015 The LianLianYT PAY SDK Source Project
   * All right reserved.
   * @Title:  EntireFactorActivity.java   
   * @Package com.yintong.activity   
   * @author: Marco Jin     
   * @date:   2017年2月6日 下午2:37:26       
*/  
package com.bb.taold.activitiy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bb.taold.R;
import com.bb.taold.lianlian.env.EnvConstants;
import com.bb.taold.lianlian.utils.BaseHelper;
import com.bb.taold.lianlian.utils.LLPayConstants;
import com.bb.taold.lianlian.utils.EntireFactorBean;
import com.bb.taold.lianlian.utils.HttpRequest;
import com.bb.taold.lianlian.utils.MobileSecurePayer;
import com.bb.taold.lianlian.utils.PayOrder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntireFactorPayActivity extends Activity {

    private Button jump_btn;
    private Button pay_btn;
//    private TestButton ts_btn;
    
    private String mUseID;
    private String mAPIVersion;
    private String mPartnerID;
    private String mToken;

    private boolean isTestServer = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entirefactorpay);
//        ts_btn = (TestButton) findViewById(LLRes.id.test_btn);
//        ts_btn.init();
        
        jump_btn = (Button) findViewById(R.id.jump_btn);

        jump_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("jump_btn", "onClick: here");

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        JSONObject signBean = BaseHelper.Object2JSON(constructCreatBillBean());
                        JSONObject resultObj = HttpRequest.request(getApplicationContext(), signBean, LLPayConstants.CREATBILLURL);
                        String retcode = resultObj.optString("ret_code", "");
                        Log.i("jump_btn", "request: " + resultObj);
                        if ("0000".equals(retcode))
                        {
                            mToken = resultObj.optString("token", "");

                            MobileSecurePayer payer = new MobileSecurePayer();
//                            payer.setCAPTCHA_Switch(true);
                            payer.setCallbackHandler(mHandler, LLPayConstants.RQF_SIGN);
                            payer.setTestMode(isTestServer);

                            boolean bRet = payer.doTokenSign(resultObj, EntireFactorPayActivity.this);
//                            boolean bRet = payer.pay(BaseHelper.toJSONString(constructSignBean()), mHandler,
//                                    LLPayConstants.RQF_PAY, EntireFactorPayActivity.this, "", true, true, PAY_MODE.PAYMODE_TOKEN_SIGN);
                        }else{

                            showResult(resultObj);
                        }
                    }
                }).start();

            }
        });

        pay_btn = (Button) findViewById(R.id.pay_button);

        pay_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("pay_btn", "onClick: here");

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        JSONObject signBean = BaseHelper.Object2JSON(constructCreatPayBillBean());
                        JSONObject result = HttpRequest.request(getApplicationContext(), signBean, LLPayConstants.CREATPayBILLURL);
                        try {
                            result.put("money_order", signBean.opt("money_order"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String retcode = result.optString("ret_code", "");
                        Log.i("pay_btn", "request: " + result);
                        if ("0000".equals(retcode))
                        {
                            //mToken = result.optString("token", "");

                            MobileSecurePayer payer = new MobileSecurePayer();
//                            payer.setCAPTCHA_Switch(true);
                            payer.setCallbackHandler(mHandler, LLPayConstants.RQF_PAY);
                            payer.setTestMode(isTestServer);

                            boolean bRet = payer.doPay(result, EntireFactorPayActivity.this);
                        }
                        else{

                            showResult(result);
                        }
                    }
                }).start();

            }
        });

        //PermissionsUtil.checkAndRequestPermissions(this);
    }

    private void showResult(JSONObject resultObj){
        Looper.prepare();

        BaseHelper.showDialog(EntireFactorPayActivity.this, "创单提示",
                "创单失败：" + resultObj.optString("ret_code")+" 返回报文:"+resultObj.toString(),
                android.R.drawable.ic_dialog_alert);

        Looper.loop();
    }

    private Handler mHandler = createHandler();

    private Handler createHandler() {
        return new Handler() {
            public void handleMessage(Message msg) {
                String strRet = (String) msg.obj;
                switch (msg.what) {
                    case LLPayConstants.RQF_PAY:
                    case LLPayConstants.RQF_SIGN:
                    {
                        JSONObject objContent = BaseHelper.string2JSON(strRet);
                        String retCode = objContent.optString("ret_code");
                        String retMsg = objContent.optString("ret_msg");

                        // 成功
                        if (LLPayConstants.RET_CODE_SUCCESS.equals(retCode)) {

                            BaseHelper.showDialog(EntireFactorPayActivity.this, "提示",
                                        "支付成功，交易状态码：" + retCode+" 返回报文:"+strRet,
                                        android.R.drawable.ic_dialog_alert);
    
                        }  else {
                            // TODO 失败
                            BaseHelper.showDialog(EntireFactorPayActivity.this, "错误提示", retMsg
                                    + "，交易状态码:" + retCode+" 返回报文:"+strRet,
                                    android.R.drawable.ic_dialog_alert);
                        }
                    }
                        break;
                }
                super.handleMessage(msg);
            }
        };

    }

    private EntireFactorBean constructCreatBillBean() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);

        mUseID = ((EditText) findViewById(R.id.userid)).getText().toString();
        String IDNo = ((EditText) findViewById(R.id.idcard)).getText().toString();
        String accountName = ((EditText) findViewById(R.id.name)).getText().toString();
        String bankCardNo = ((EditText) findViewById(R.id.bankno)).getText().toString();
        String cellNo = ((EditText) findViewById(R.id.cellno)).getText().toString();
//        String cellNo = "18857185435";
        
        String IDType = "0";
        mAPIVersion = "1.0";
        
        mPartnerID = EnvConstants.PARTNER;
        
        // 风险控制参数
//        order.setRisk_item(constructRiskItem());

        // RSA 签名方式
        String signType = PayOrder.SIGN_TYPE_RSA;
        String sign = "请求参数签名串";
//        String content = BaseHelper.sortParam(order);
//        // RSA 签名方式
//         sign = Rsa.sign(content, EnvConstants.RSA_PRIVATE);
        
        EntireFactorBean bean = new EntireFactorBean();
        bean.setAcct_name(accountName);
        bean.setApi_version(mAPIVersion);
        bean.setBind_mob(cellNo);
        bean.setId_no(IDNo);
        bean.setId_type(IDType);
        bean.setCard_no(bankCardNo);
        bean.setOid_partner(mPartnerID);
        bean.setUser_id(mUseID);
        bean.setTime_stamp(timeString);
        bean.setSign_type(signType);
        bean.setSign(sign);
        bean.setNo_order(timeString);
        bean.setFlag_pay_product("1");
        bean.setFlag_chnl("0");
        
        return bean;
    }

    private EntireFactorBean constructCreatPayBillBean() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);

        mUseID = ((EditText) findViewById(R.id.userid)).getText().toString();
        String IDNo = ((EditText) findViewById(R.id.idcard)).getText().toString();
        String accountName = ((EditText) findViewById(R.id.name)).getText().toString();
        String bankCardNo = ((EditText) findViewById(R.id.bankno)).getText().toString();
        String cellNo = ((EditText) findViewById(R.id.cellno)).getText().toString();
        String money_order = ((EditText) findViewById(R.id.money)).getText().toString();
//        String cellNo = "18857185435";

        String IDType = "0";
        mAPIVersion = "1.0";

        mPartnerID = EnvConstants.PARTNER;

        // 风险控制参数
//        order.setRisk_item(constructRiskItem());

        // RSA 签名方式
        String signType = PayOrder.SIGN_TYPE_RSA;
        String sign = "请求参数签名串";
//        String content = BaseHelper.sortParam(order);
//        // RSA 签名方式
//         sign = Rsa.sign(content, EnvConstants.RSA_PRIVATE);

        EntireFactorBean bean = new EntireFactorBean();
        bean.setAcct_name(accountName);
        bean.setApi_version(mAPIVersion);
        bean.setBind_mob(cellNo);
        bean.setId_no(IDNo);
        bean.setId_type(IDType);
        bean.setCard_no(bankCardNo);
        bean.setOid_partner(mPartnerID);
        bean.setUser_id(mUseID);
        bean.setTime_stamp(timeString);
        bean.setSign_type(signType);
        bean.setSign(sign);
        bean.setNo_order(timeString);
        bean.setFlag_pay_product("1");
        bean.setFlag_chnl("0");
        bean.setMoney_order(money_order);

        String no_agree = ((EditText) findViewById(R.id.agree_no)).getText().toString();
        bean.setNo_agree(no_agree);


        return bean;
    }
    
    private EntireFactorBean constructSignBean() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date = new Date();
        String timeString = dataFormat.format(date);

        mUseID = ((EditText) findViewById(R.id.userid)).getText().toString();
        mAPIVersion = "1.0";
        
        mPartnerID = EnvConstants.PARTNER;
//        mPartnerID = "201605130000138008";
        
        // 风险控制参数
//        order.setRisk_item(constructRiskItem());

        // RSA 签名方式
        String signType = PayOrder.SIGN_TYPE_RSA;
        String sign = "请求参数签名串";
//        String content = BaseHelper.sortParam(order);
//        // RSA 签名方式
//         sign = Rsa.sign(content, EnvConstants.RSA_PRIVATE);
        
        EntireFactorBean bean = new EntireFactorBean();
        bean.setApi_version(mAPIVersion);
        bean.setOid_partner(mPartnerID);
        bean.setUser_id(mUseID);
        bean.setToke(mToken);
        bean.setTime_stamp(timeString);
        bean.setSign_type(signType);
        bean.setSign(sign);
        
        return bean;
    }

//    private final String BANK_URL = "http://www.baidu.com/";
//    private void mockRequestBank(){
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                JSONObject signBean = BaseHelper.Object2JSON(constructCreatPayBillBean());
//                JSONObject result = HttpRequest.request(getApplicationContext(), signBean, BANK_URL);
//
//                MobileSecurePayer payer = new MobileSecurePayer();
//                payer.setCallbackHandler(mHandler, LLPayConstants.RQF_SIGN);
//                payer.setTestMode(true);
//                boolean bRet = payer.doTokenSign(result, EntireFactorPayActivity.this);
//            }
//        }).start();
//    }
}

