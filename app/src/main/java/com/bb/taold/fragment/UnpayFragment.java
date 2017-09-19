package com.bb.taold.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.repay.RepayDetailActivity;
import com.bb.taold.adapter.UnpayBillAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.WaitRepayRecord;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;

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

    @BindView(R.id.tv_totalMoney)
    TextView mTvTotalMoney;

    @BindView(R.id.tv_restAmount)
    TextView mTvRestAmount;

    @BindView(R.id.tv_tiptext)
    TextView mTvTiptext;

    //接口返回接受
    private PostCallback postCallback;
    //获取未还账单分期
    private WaitRepayRecord info = null;
    //未还款账单的id
    private String billId = "";
    private UnpayBillAdapter unpayBillAdapter;

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
        //页面初始获取未还账单页面
        getUnpayInfo();

    }

    /**
     * 获取未还账单信息
     */

    private void getUnpayInfo() {
        //获取未还款账单
        Call<Result_Api<WaitRepayRecord>> call = service.waitRepayRecord();
        Callexts.need_sessionPost(call, new PostCallback<BaseFragment>() {
            @Override public void successCallback(Result_Api api) {
                mSwiperRefresh.setRefreshing(false);
                if (api.getT() instanceof WaitRepayRecord) {
                    info = (WaitRepayRecord) api.getT();
                    billId = info.getBillId();
                    //向列表中写入数据
                    UnpayBillAdapter mAdapter = new UnpayBillAdapter(getActivity(), info);
                    mLvUnpayBill.setAdapter(mAdapter);

                    //设置总共应还金额
                    mTvTotalMoney.setText(info.getShouldPayAmt());
                    mTvRestAmount.setText(info.getWaitPayAmt());
                    if (info.getBillItems().size() > 0) {
                        //设置提示
                        mTvTiptext.setText(getString(R.string.unpay_text, info.getBillItems().get(0).getRepayDate()));
                    }
                    return;
                }
            }

            @Override public void failCallback() {
                mSwiperRefresh.setRefreshing(false);
            }
        });
    }

    @OnClick(R.id.rl_billinfo)
    public void onViewClicked() {
        //跳转到还款详情页面
        Bundle mBundle = new Bundle();
        mBundle.putString("billId", billId);
        AppManager.getInstance().showActivity(RepayDetailActivity.class, mBundle);
    }
}