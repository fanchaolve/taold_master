package com.bb.taold.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import retrofit2.Call;

/**
 * ==============================================
 * <p>
 * 包名：绑定银行卡
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/16
 * <p>
 * ==============================================
 */

public class SetMainCardActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.tv_acctNo)
    TextView tv_acctNo;


    @BindView(R.id.tv_acctName)
    TextView tv_acctName;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.btn_back)
    ImageButton btn_back;

    @BindView(R.id.tv_next)
    TextView tv_next;

    private CardCheck cardCheck;//判断是否为有效卡

    private UserInfo info;//

    PostCallback postCallback =new PostCallback<BaseActivity>(this) {
        @Override
        public void successCallback(Result_Api api) {
            if(api.getT() instanceof UserInfo){
                info= (UserInfo) api.getT();


            }
        }

        @Override
        public void failCallback() {

        }
    };



    @Override
    public int getLayoutId() {
        return R.layout.activity_setmaincard;
    }

    @Override
    public void initView() {
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(this);
        tv_title.setText("绑定银行卡");
        tv_next.setOnClickListener(this);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        Intent intent =getIntent();
        if(intent!=null){
            Bundle bundle =intent.getExtras();
            if(bundle!=null && bundle.containsKey("card")){
                cardCheck= (CardCheck) bundle.getSerializable("card");
            }
        }
        if(cardCheck ==null)
            return;
        tv_acctNo.setText(cardCheck.getCardNo().replaceAll("\\d{4}(?!$)", "$0 "));
        tv_acctName.setText(cardCheck.getBankName());

        Call<Result_Api<UserInfo>>call=service.user_info();
        Callexts.need_sessionPost(call,postCallback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_next:
                if(cardCheck == null && info == null) {
                    showTip("获取数据异常");
                    return;
                }


//                Call<Result_Api>call =service.setToMaster(cardCheck.getCardNo());
//                Callexts.need_sessionPost(call,postCallback);
                break;
        }
    }
}
