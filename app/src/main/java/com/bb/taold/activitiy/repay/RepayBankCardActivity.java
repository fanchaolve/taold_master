package com.bb.taold.activitiy.repay;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.cardList.CardListActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardInfo;
import com.bb.taold.bean.Cardinfos;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 银行卡还款接口
 * Created by zhouwan on 2017/9/22.
 */

public class RepayBankCardActivity extends BaseActivity {
    @BindView(R.id.btn_back) ImageButton btnBack;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_bankname) TextView tvBankname;
    @BindView(R.id.tv_cardinfo) TextView tvCardinfo;
    @BindView(R.id.tv_cardtype) TextView tvCardtype;
    @BindView(R.id.rl_card) RelativeLayout rlCard;
    @BindView(R.id.card_textView) TextView cardTextView;

    @Override public int getLayoutId() {
        return R.layout.repay_bank_card_activity;
    }

    @Override public void initdata() {
        getCardData();
    }

    @Override public void initListener() {

    }

    @Override public void initView() {
        btnBack.setVisibility(View.VISIBLE);
        tvTitle.setText("自动还款");

    }


    @OnClick({R.id.rl_card, R.id.card_textView, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_card:
                AppManager.getInstance().showActivity(CardListActivity.class, null);
                break;
            case R.id.card_textView:
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EventBus.getDefault().register(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CardInfo cardInfo) {
        setCardInfo(cardInfo);
    }

    private void setCardInfo(CardInfo cardInfo) {
        tvBankname.setText(cardInfo.getCardName());
        String cardNo = cardInfo.getCardno();
        if (cardNo.length() > 4) {
            cardNo = cardNo.substring(cardNo.length() - 4, cardNo.length());
        }
        tvCardinfo.setText("尾号" + cardNo + " " + "借记卡");
        tvCardtype.setText("借款卡");
    }

    private void getCardData() {
        //下拉刷新重新获取银行卡列表数据
        final Call<Result_Api<Cardinfos>> call = service.bankList("100");
        Callexts.need_sessionPost(call, new PostCallback<RepayBankCardActivity>(this) {
            @Override public void successCallback(Result_Api api) {
                if (api.getT() != null) {
                    List<CardInfo> cards = (ArrayList<CardInfo>) api.getT();
                    if (cards != null) {
                        if (cards.get(0) != null) {
                            setCardInfo(cards.get(0));
                        }
                    }
                }
            }

            @Override public void failCallback() {

            }
        });
    }

}
