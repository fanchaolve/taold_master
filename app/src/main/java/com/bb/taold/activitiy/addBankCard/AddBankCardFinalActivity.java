package com.bb.taold.activitiy.addBankCard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.listener.Callexts;

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
    @BindView(R.id.tv_acctNo)
    TextView mEtAcctNo;
    @BindView(R.id.tv_acctName)
    TextView mEtAcctName;
    @BindView(R.id.et_acctPhone)
    EditText mEtAcctPhone;



    private CardCheck cardCheck;//判断是否为有效卡

    private UserInfo info;//

    private PostCallback postCallback  = new PostCallback(this) {
        @Override
        public void successCallback(Result_Api api) {
            if(api.getT() instanceof UserInfo){
                info= (UserInfo) api.getT();
                if(info== null)
                    return;
                mEtAcctUser.setText(info.getRealName());
            }else if(api.getT() instanceof  String){

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
        //设置标题
        mTvTitle.setText("添加银行卡");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        Intent intent =getIntent();
        if(intent!=null){
            Bundle bundle =intent.getExtras();
            if(bundle.containsKey("card")){
                cardCheck= (CardCheck) bundle.getSerializable("card");
            }
        }
        if(cardCheck ==null)
            return;
        mEtAcctNo.setText(cardCheck.getCardNo().replaceAll("\\d{4}(?!$)", "$0 "));
        mEtAcctName.setText(cardCheck.getBankName());
        Call<Result_Api<UserInfo>>call=service.user_info();
        Callexts.need_sessionPost(call,postCallback);


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


                if(TextUtils.isEmpty(mEtAcctPhone.getText().toString())){
                    showTip("请填写预留手机号");
                    break;
                }
                if(cardCheck == null && info == null) {
                    showTip("未获取卡片或者个人信息");
                    return;
                }

                Call<Result_Api <String>> call = service.createNewBankCard(cardCheck.getBankCode(),
                        info.getRealName(),cardCheck.getCardNo().replace(" ",""),
                        info.getIdCard(),cardCheck.getBankName(),
                        mEtAcctPhone.getText().toString());
                Callexts.need_sessionPost(call,postCallback);

//                Call<Result_Api <String>> call = service.removeCard(cardCheck.getCardNo().replace(" ",""));
//                Callexts.need_sessionPost(call,postCallback);
                break;
        }
    }
}
