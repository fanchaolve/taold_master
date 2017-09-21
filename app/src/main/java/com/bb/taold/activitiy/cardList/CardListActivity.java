package com.bb.taold.activitiy.cardList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.addBankCard.AddBankCardFinalActivity;
import com.bb.taold.adapter.CardListAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.CardCheck;
import com.bb.taold.bean.CardInfo;
import com.bb.taold.bean.Cardinfos;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CardNumScanUtil;
import com.bb.taold.widget.SwipeListLayout;
import com.idcard.TFieldID;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;
    @BindView(R.id.lv_cardlist)
    ListView mLvCardlist;

    private static Set<SwipeListLayout> sets = new HashSet();

    //银行卡卡号
    private String cardNo = "";

    private PostCallback postCallback;
    private ArrayList<CardInfo> mCards;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cardlist;
    }

    @Override
    public void initView() {
        mBtnBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("管理银行卡");
    }

    @Override
    public void initListener() {

    }


    @Override
    public void initdata() {

        //设置列表滑动监听和下拉刷新
        setListData();

        postCallback = new PostCallback<BaseActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof Cardinfos) {
                    mCards = (ArrayList<CardInfo>) api.getT();
                    //设置列表适配器
                    mLvCardlist.setAdapter(new CardListAdapter(CardListActivity.this, mCards));

                    mSwiperRefresh.setRefreshing(false);
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

    private void setListData() {
        mLvCardlist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mSwiperRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getCardData();

            }
        });
        mLvCardlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardInfo cardInfo = mCards.get(position);
                if (cardInfo != null) {
                    EventBus.getDefault().post(cardInfo);
                }
            }
        });
    }

    private void getCardData() {
        //下拉刷新重新获取银行卡列表数据
        Call<Result_Api<Cardinfos>> call = service.bankList("10");
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
     * 监听侧滑事件
     */
    public static class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

        private SwipeListLayout slipListLayout;

        public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
            this.slipListLayout = slipListLayout;
        }

        @Override
        public void onStatusChanged(SwipeListLayout.Status status) {
            if (status == SwipeListLayout.Status.Open) {
                //若有其他的item的状态为Open，则Close，然后移除
                if (sets.size() > 0) {
                    for (SwipeListLayout s : sets) {
                        s.setStatus(SwipeListLayout.Status.Close, true);
                        sets.remove(s);
                    }
                }
                sets.add(slipListLayout);
            } else {
                if (sets.contains(slipListLayout))
                    sets.remove(slipListLayout);
            }
        }

        @Override
        public void onStartCloseAnimation() {

        }

        @Override
        public void onStartOpenAnimation() {

        }

    }

    /**
     * 监听银行卡识别回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
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
