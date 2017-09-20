package com.bb.taold.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.repay.RepayInfoActivity;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.BillItem;
import com.bb.taold.bean.BillInfoDetail;
import com.bb.taold.bean.BillProductInfo;
import com.bb.taold.bean.WaitRepayRecord;
import com.bb.taold.utils.AppManager;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name CardListAdapter
 */
public class UnpayBillAdapter extends ListBaseAdapter<BillItem> {
    private String stages = "";


    public UnpayBillAdapter(Context context) {
        super(context);
    }

    @Override public int getLayoutId() {
        return R.layout.item_unpay;
    }

    @Override public void onBindItemHolder(SuperViewHolder holder, int position) {
        final BillItem billItem = mDataList.get(position);
        TextView tv_status = holder.getView(R.id.tv_paybtn);
        TextView tv_period = holder.getView(R.id.tv_period);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_amount = holder.getView(R.id.tv_loanAmount);
        //设置期数
        tv_period.setText(billItem.getStages().toString() + "/" + stages + "期");
        //设置时间
        tv_time.setText(billItem.getRepayDate().toString());
        //设置金额
        tv_amount.setText(billItem.getAmtMoney().toString());
        //设置状态
        if (billItem.getIsOverdue().equals("0")) {
            tv_status.setText("待还款");
        } else {
            tv_status.setText("已逾期");
        }
        //还款按钮
        holder.getView(R.id.tv_paybtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                if (billItem.getIsOverdue().equals("false")) {
                    mBundle.putString("bill_status", "0");
                } else {
                    mBundle.putString("bill_status", "2");
                }
                mBundle.putString("billItemId", billItem.getBillItemId());
                AppManager.getInstance().showActivity(RepayInfoActivity.class, mBundle);
            }
        });

    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }


}