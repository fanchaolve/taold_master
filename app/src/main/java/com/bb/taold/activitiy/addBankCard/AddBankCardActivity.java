package com.bb.taold.activitiy.addBankCard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CardNumScanUtil;
import com.idcard.CardInfo;
import com.idcard.TFieldID;
import com.turui.bank.ocr.CaptureActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by zhucheng'an on 2017/9/11.
 * Package Name com.bb.taold.activitiy.addBankCard
 * Class Name AddBankCardActivity
 */

public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.iv_take)
    ImageView iv_take;

    private String cardNo = "";//卡号

    private PostCallback postCallback = new PostCallback(this) {
        @Override
        public void successCallback(Result_Api api) {

            if (api.getT() instanceof CardCheck) {
                CardCheck cardCheck= (CardCheck) api.getT();
                if (cardCheck == null)
                    return;
                Bundle bundle =new Bundle();
                cardCheck.setCardNo(cardNo);
                bundle.putSerializable("card",cardCheck);
                AppManager.getInstance().showActivity(AddBankCardFinalActivity.class,bundle);
            }
        }

        @Override
        public void failCallback() {

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_addcard;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("绑定银行卡");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }


    @OnClick({R.id.iv_take, R.id.tv_next, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_take:
                //添加银行卡页面
                CardNumScanUtil.getINSTANCE().doScan();
                break;
            case R.id.tv_next:
//                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//                View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_common, null);
//                dialog.setView(dialogView);
//                TextView tv_cancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
//                TextView tv_content = (TextView) dialogView.findViewById(R.id.tv_content);
//                tv_content.setText("检测到您添加的银行卡非借记卡，请重新添加一张借记卡");
//                dialog.create();
//                final AlertDialog dialog2 = dialog.show();
//                //点击对话框外的部分对话框不消失
//                dialog2.setCanceledOnTouchOutside(false);
//                tv_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //点击"我知道了"按钮对话框消失
//                        dialog2.dismiss();
//                    }
//                });
                getCardState(cardNo);

                break;
            case R.id.btn_back:
                finish();
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
                CardInfo cardInfo = (CardInfo) data.getSerializableExtra("cardinfo");
                cardNo = cardInfo.getFieldString(TFieldID.TBANK_NUM);
                cardNo = cardNo.replace(" ", "").trim();
                //tvCardNo.setText(cardNo);
                //银行卡照片

                Bitmap bitmap = CaptureActivity.TakeBitmap;
                if (bitmap == null)
                    return;
                iv_take.setImageBitmap(bitmap);
            }
        }
    }

    private void getCardState(String ordNo) {
        Call<Result_Api<CardCheck>> call=service.supportCard(ordNo);
        Callexts.need_sessionPost(call,postCallback);
    }
}
