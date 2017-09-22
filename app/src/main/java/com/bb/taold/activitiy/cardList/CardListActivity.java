package com.bb.taold.activitiy.cardList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.addBankCard.AddBankCardFinalActivity;
import com.bb.taold.adapter.SwiperMenuAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.CardInfo;
import com.bb.taold.bean.Cardinfos;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CardNumScanUtil;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.idcard.TFieldID;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by zhucheng'an on 2017/9/11.
 * Package Name com.bb.taold.activitiy.cardList
 * Class Name CardListActivity
 */

public class CardListActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_card_list)
    LRecyclerView mRecyclerView;
    //银行卡卡号
    private String cardNo = "";

    private PostCallback postCallback;
    private List<CardInfo> mCards = new ArrayList<>();
    private SwiperMenuAdapter mSwiperAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cardlist;
    }

    @Override
    public void initdata() {

        postCallback = new PostCallback<BaseActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                mRecyclerView.refreshComplete(0);
                if (api.getT() instanceof Cardinfos) {
                    List<CardInfo> cards = (ArrayList<CardInfo>) api.getT();
                    mSwiperAdapter.addAll(cards);
                }

                if (api.getT() instanceof CardCheck) {
                    CardCheck cardCheck = (CardCheck) api.getT();
                    if (cardCheck == null)
                        return;
                    Bundle bundle = new Bundle();
                    bundle.putInt("from_Act", 1);
                    cardCheck.setCardNo(cardNo);
                    bundle.putSerializable("card", cardCheck);
                    AppManager.getInstance().showActivity(AddBankCardFinalActivity.class, bundle);

                }

            }

            @Override
            public void failCallback() {

            }
        };

        getCardData();

    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("管理银行卡");
        mSwiperAdapter = new SwiperMenuAdapter(mContext);
        mSwiperAdapter.setDataList(mCards);
        mRecyclerView.setAdapter(new LRecyclerViewAdapter(mSwiperAdapter));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initListener() {
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override public void onRefresh() {
                mSwiperAdapter.clear();
                getCardData();
            }
        });
    }

    private void getCardData() {
        //下拉刷新重新获取银行卡列表数据
        Call<Result_Api<Cardinfos>> call = service.bankList("100");
        Callexts.need_sessionPost(call, postCallback);
    }

    /**
     * 解除绑定按钮
     */
    public void removeCard(String cardId) {
        //下拉刷新重新获取银行卡列表数据
        Call<Result_Api<String>> call = service.removeCard(cardId);
        Callexts.need_sessionPost(call, postCallback);
    }

    /**
     * 监听银行卡识别回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == CardNumScanUtil.RESULT_GET_OK) {
                // 处理银行卡扫描结果（在界面上显示）
                if (data == null) {
                    return;
                }
                com.idcard.CardInfo cardInfo = (com.idcard.CardInfo) data.getSerializableExtra("cardinfo");
                cardNo = cardInfo.getFieldString(TFieldID.TBANK_NUM);
                cardNo = cardNo.replace(" ", "").trim();
                if (TextUtils.isEmpty(cardNo)) {
                    return;
                }
                getCardState(cardNo);

            }
        }
    }

    /**
     * 判断银行卡是否有效
     *
     * @param ordNo
     */
    private void getCardState(String ordNo) {
        Call<Result_Api<CardCheck>> call = service.supportCard(ordNo);
        Callexts.need_sessionPost(call, postCallback);
    }

    @OnClick({R.id.btn_back, R.id.tv_addcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_addcard:
                //添加银行卡
                CardNumScanUtil.getINSTANCE().doScan();
                break;
        }
    }
}
