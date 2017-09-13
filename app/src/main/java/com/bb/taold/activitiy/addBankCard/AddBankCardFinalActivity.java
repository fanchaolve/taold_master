package com.bb.taold.activitiy.addBankCard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.HomeActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.Session;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.et_acctUser)
    EditText mEtAcctUser;
    @BindView(R.id.et_acctNo)
    EditText mEtAcctNo;
    @BindView(R.id.et_acctName)
    EditText mEtAcctName;
    @BindView(R.id.et_acctPhone)
    EditText mEtAcctPhone;

    private PostCallback postCallback;//接口返回接受

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
        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                //判断哪个接口回调
                if(getFlag() == 0){
                    //判断银行卡是否有效
                    CardCheck cardInfo = (CardCheck) api.getT();
                    //获取bankCode传给添加银行卡接口
                    Call call = service.createNewBankCard(cardInfo.getBankCode(),
                            mEtAcctUser.getText().toString(),mEtAcctNo.getText().toString(),
                            "330727199412300057",mEtAcctName.getText().toString(),
                            mEtAcctPhone.getText().toString());
                    postCallback.setFlag(1);
                    call.enqueue(postCallback);
                    return;
                }

                if(getFlag() == 1){
                    //添加银行卡
                    return;
                }
            }

            @Override
            public void failCallback() {

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                //判断信息是否填写完整
                if(TextUtils.isEmpty(mEtAcctUser.getText().toString())){
                    showTip("请填写持卡人姓名");
                    return;
                }
                if(TextUtils.isEmpty(mEtAcctNo.getText().toString())){
                    showTip("请填写银行卡号");
                    break;
                }
                if(TextUtils.isEmpty(mEtAcctName.getText().toString())){
                    showTip("请填写银行名称");
                    break;
                }
                if(TextUtils.isEmpty(mEtAcctPhone.getText().toString())){
                    showTip("请填写预留手机号");
                    break;
                }
                //调用判断银行卡是否有效接口
                Call call = service.supportCard(mEtAcctNo.getText().toString());
                postCallback.setFlag(0);
                call.enqueue(postCallback);
                break;
        }
    }
}
