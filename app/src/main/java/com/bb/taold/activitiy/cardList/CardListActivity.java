package com.bb.taold.activitiy.cardList;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.CardListAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.widget.SwipeListLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.lv_cardlist)
    ListView mLvCardlist;

    private static Set<SwipeListLayout> sets = new HashSet();
    //数组保存银行卡数据
    private ArrayList<String> list = new ArrayList<>();

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

        initList();
        //设置列表适配器
        mLvCardlist.setAdapter(new CardListAdapter(this,list));

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
    }

    private void initList() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        list.add("I");
        list.add("J");
        list.add("K");
        list.add("L");
        list.add("M");
        list.add("N");
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
