package com.bb.taold.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bb.taold.R;
import com.bb.taold.adapter.AllpayBillAdapter;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.RepayDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.fragment
 * Class Name UnpayFragment
 * 未还款账单
 */

public class AllpayFragment extends BaseFragment {
    @BindView(R.id.lv_allpay)
    ListView mLvAllpay;
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;

    ArrayList<RepayDetail> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_allpay;
    }

    @Override
    public void initView() {

        initList();

        AllpayBillAdapter mAdapter = new AllpayBillAdapter(getActivity(), mList);

        mLvAllpay.setAdapter(mAdapter);

        mSwiperRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showTip("refresh");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSwiperRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });

    }

    public void initList() {
        for (int i = 0; i < 2; i++) {
            RepayDetail mUnpay = new RepayDetail();
            mUnpay.setAmount("1000");
            mUnpay.setPeriod("1/12");
            mUnpay.setStatus("已还清");
            mUnpay.setTime("2017/09/20 17:20:18");
            mList.add(mUnpay);
        }
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

}
