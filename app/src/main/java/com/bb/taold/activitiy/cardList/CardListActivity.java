package com.bb.taold.activitiy.cardList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
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
import com.bb.taold.bean.UserInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.widget.SwipeListLayout;

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

    //数组保存银行卡数据
    private ArrayList<String> list = new ArrayList<>();

    private PostCallback postCallback;

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

        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                if(api.getT() instanceof Cardinfos){

                    ArrayList<CardInfo> cards = (ArrayList<CardInfo>)api.getT();
                    //设置列表适配器
                    mLvCardlist.setAdapter(new CardListAdapter(CardListActivity.this, cards));

                    mSwiperRefresh.setRefreshing(false);
                }

            }

            @Override
            public void failCallback() {

            }
        };

        getCardData();

    }

    private void setListData(){
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
    }

    private void getCardData(){
        //下拉刷新重新获取银行卡列表数据
        Call<Result_Api<Cardinfos>> call=service.bankList("10");
        Callexts.need_sessionPost(call,postCallback);
    }

    /**
     * 解除绑定按钮
     */
    public void removeCard(String cardId){
        //下拉刷新重新获取银行卡列表数据
        Call<Result_Api<String>> call=service.removeCard(cardId);
        Callexts.need_sessionPost(call,postCallback);
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

    @OnClick({R.id.btn_back, R.id.tv_addcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_addcard:
                break;
        }
    }
}
