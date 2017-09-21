package com.bb.taold.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bb.taold.R;
import com.bb.taold.adapter.AllpayBillAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.AllBill;
import com.bb.taold.listener.Callexts;
import com.bb.taold.widget.EmptyView;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
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
    LRecyclerView mLvAllpay;
    @BindView(R.id.empty_view) EmptyView emptyView;
    Unbinder unbinder;
    //记录当前查询历史记录的页数
    private int currentPage = 1;
    private AllpayBillAdapter allpayBillAdapter;
    private LRecyclerViewAdapter recyclerViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_allpay;
    }

    @Override
    public void initView() {
        allpayBillAdapter = new AllpayBillAdapter(getActivity());
        recyclerViewAdapter = new LRecyclerViewAdapter(allpayBillAdapter);
        mLvAllpay.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLvAllpay.setAdapter(recyclerViewAdapter);
        mLvAllpay.setLoadMoreEnabled(false);
        mLvAllpay.setOnRefreshListener(new OnRefreshListener() {
            @Override public void onRefresh() {
                //页面初始获取未还账单页面
                getAllpayInfo();
            }
        });
        mLvAllpay.forceToRefresh();
    }

    public void getAllpayInfo() {
        //获取已还款账单
        Call<Result_Api<AllBill>> call = service.queryBillInfo(currentPage + "", Integer.MAX_VALUE + "");
        Callexts.need_sessionPost(call, new PostCallback<BaseFragment>() {
            @Override
            public void successCallback(Result_Api api) {
                mLvAllpay.refreshComplete(0);
                //判断哪个接口回调
                if (api.getT() instanceof AllBill) {

                    AllBill mBill = (AllBill) api.getT();
                    //添加数据
                    if (mBill != null) {
                        allpayBillAdapter.addAll(mBill.getRows());
                    }
                }
                allpayBillAdapter.notifyDataSetChanged();
                if (allpayBillAdapter.getItemCount() > 0) {
                    emptyView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failCallback() {

            }
        });
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
