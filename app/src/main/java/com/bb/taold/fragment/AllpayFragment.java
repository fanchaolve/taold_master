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
import com.bb.taold.adapter.UnpayBillAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.AllBill;
import com.bb.taold.bean.BillInfo;
import com.bb.taold.bean.BillInfos;
import com.bb.taold.bean.RepayDetail;
import com.bb.taold.listener.Callexts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

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

    //接口返回接受
    private PostCallback postCallback;
    //记录当前查询历史记录的页数
    private int currentPage = 1;
    //记录所有借款记录
    ArrayList<BillInfo> billInfos = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_allpay;
    }

    @Override
    public void initView() {

    }

    public void getAllpayInfo() {
        //获取已还款账单
        Call<Result_Api<AllBill>> call=service.queryBillInfo(currentPage+"","10");
        Callexts.need_sessionPost(call,postCallback);
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        //列表刷新时重新获取数据
        mSwiperRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage += 1;
                getAllpayInfo();
            }
        });

        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {

                mSwiperRefresh.setRefreshing(false);
                //判断哪个接口回调
                if(api.getT() instanceof AllBill){

                    AllBill mBill = (AllBill)api.getT();
                    //添加数据
                    billInfos.addAll(mBill.getRows());
                    AllpayBillAdapter mAdapter = new AllpayBillAdapter(getActivity(), billInfos);
                    mLvAllpay.setAdapter(mAdapter);
                }
            }

            @Override
            public void failCallback() {

            }
        };

        //页面初始获取未还账单页面
        getAllpayInfo();
    }

}
