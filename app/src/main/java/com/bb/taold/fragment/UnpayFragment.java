package com.bb.taold.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.bb.taold.R;
import com.bb.taold.adapter.UnpayBillAdapter;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.RepayDetail;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.fragment
 * Class Name UnpayFragment
 * 未还款账单
 */

public class UnpayFragment extends BaseFragment {
    @BindView(R.id.lv_unpay_bill)
    ListView mLvUnpayBill;

    ArrayList<RepayDetail> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unpay;
    }

    @Override
    public void initView() {

        initList();

        UnpayBillAdapter mAdapter = new UnpayBillAdapter(getActivity(),mList);

        mLvUnpayBill.setAdapter(mAdapter);
    }

    public void initList(){
        for(int i=0;i<2;i++){
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
