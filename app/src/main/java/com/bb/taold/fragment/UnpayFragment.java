package com.bb.taold.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.bb.taold.R;
import com.bb.taold.activitiy.repay.RepayDetailActivity;
import com.bb.taold.adapter.UnpayBillAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.BillInfo;
import com.bb.taold.bean.BillInfos;
import com.bb.taold.bean.RepayDetail;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.fragment
 * Class Name UnpayFragment
 * 未还款账单
 */

public class UnpayFragment extends BaseFragment {
    @BindView(R.id.lv_unpay_bill)
    ListView mLvUnpayBill;

    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;

    //接口返回接受
    private PostCallback postCallback;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unpay;
    }

    @Override
    public void initView() {


    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

        //列表刷新时重新获取数据
        mSwiperRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUnpayInfo();
            }
        });

        postCallback = new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                mSwiperRefresh.setRefreshing(false);
                //判断哪个接口回调
                if (api.getT() instanceof BillInfos) {
                    ArrayList<BillInfo> mList = (BillInfos)api.getT();
                    //根据账单id获取账单详情
                    getUnpayInfoDetail(mList.get(0).getId());
                    return;
                }

                if(api.getT() instanceof String){
                    //向列表中写入数据
                    UnpayBillAdapter mAdapter = new UnpayBillAdapter(getActivity(), null);
                    mLvUnpayBill.setAdapter(mAdapter);
                    return;
                }
            }

            @Override
            public void failCallback() {

            }
        };

        //页面初始获取未还账单页面
        getUnpayInfo();

    }

    /**
     * 获取未还账单信息
     */
    private void getUnpayInfo() {
        //获取未还款账单
        Call<Result_Api<BillInfos>> call = service.applyMiniBill("10");
        Callexts.need_sessionPost(call, postCallback);
    }

    /**
     * 获取未还账单详情
     * @param billId
     */
    private void getUnpayInfoDetail(String billId){
        //获取未还款账单详情
        Call<Result_Api> call = service.queryItemInfo(billId);
        Callexts.need_sessionPost(call, postCallback);
    }

    @OnClick(R.id.rl_billinfo)
    public void onViewClicked() {
        //跳转到还款详情页面
        AppManager.getInstance().showActivity(RepayDetailActivity.class,null);
    }
}