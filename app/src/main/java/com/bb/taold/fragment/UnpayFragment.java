package com.bb.taold.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.bb.taold.widget.EmptyView;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

//import com.bb.taold.bean.EventType;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.fragment
 * Class Name UnpayFragment
 * 未还款账单
 */

public class UnpayFragment extends BaseFragment {
    @BindView(R.id.lv_unpay_bill)
    LRecyclerView mLvUnpayBill;
    TextView tvTiptext;
    TextView tvTotalMoney;
    TextView tvRestAmount;
    RelativeLayout rlBillinfo;
    Unbinder unbinder;
    @BindView(R.id.empty_view) EmptyView emptyView;
    Unbinder unbinder1;
    //获取未还账单分期
    private WaitRepayRecord info = null;
    //未还款账单的id
    private String billId = "";
    private UnpayBillAdapter unpayBillAdapter;
    private LRecyclerViewAdapter recyclerViewAdapter;
    private View headView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unpay;
    }

    @Override
    public void initView() {
        unpayBillAdapter = new UnpayBillAdapter(getActivity());
        recyclerViewAdapter = new LRecyclerViewAdapter(unpayBillAdapter);
        mLvUnpayBill.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLvUnpayBill.setAdapter(recyclerViewAdapter);
        mLvUnpayBill.setLoadMoreEnabled(false);
        mLvUnpayBill.setOnRefreshListener(new OnRefreshListener() {
            @Override public void onRefresh() {
                //页面初始获取未还账单页面
                getUnpayInfo();
            }
        });
        addHeadView();

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        mLvUnpayBill.forceToRefresh();

    }

    private void addHeadView() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.bill_layout, null);
        headView.setVisibility(View.GONE);
        tvTiptext = (TextView) headView.findViewById(R.id.tv_tiptext);
        tvTotalMoney = (TextView) headView.findViewById(R.id.tv_totalMoney);
        tvRestAmount = (TextView) headView.findViewById(R.id.tv_restAmount);
        rlBillinfo = (RelativeLayout) headView.findViewById(R.id.rl_billinfo);
        rlBillinfo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Bundle mBundle = new Bundle();
                mBundle.putString("billId", billId);
                AppManager.getInstance().showActivity(RepayDetailActivity.class, mBundle);
            }
        });
        recyclerViewAdapter.addHeaderView(headView);
    }

    /**
     * 获取未还账单信息
     */

    private void getUnpayInfo() {
        //获取未还款账单
        Call<Result_Api<WaitRepayRecord>> call = service.waitRepayRecord();
        Callexts.need_sessionPost(call, new PostCallback<BaseFragment>() {
            @Override public void successCallback(Result_Api api) {
                mLvUnpayBill.refreshComplete(0);
                if (api.getT() instanceof WaitRepayRecord) {
                    info = (WaitRepayRecord) api.getT();
                    billId = info.getBillId();
                    //设置总共应还金额
                    tvTotalMoney.setText(info.getShouldPayAmt());
                    tvRestAmount.setText(info.getWaitPayAmt());
                    unpayBillAdapter.clear();
                    if (info.getBillItems() != null) {
                        if (info.getBillItems().size() > 0) {
                            //向列表中写入数据
                            unpayBillAdapter.setStages(info.getStages());
                            unpayBillAdapter.addAll(info.getBillItems());
                            //设置提示
                            tvTiptext.setText(getString(R.string.unpay_text, info.getBillItems().get(0).getRepayDate()));
                        }
                    }
                    unpayBillAdapter.notifyDataSetChanged();
                    if (unpayBillAdapter.getDataList().size() > 0) {
                        emptyView.setVisibility(View.GONE);
                        headView.setVisibility(View.VISIBLE);
                    } else {
                        headView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override public void failCallback() {

            }
        });
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

    }
}