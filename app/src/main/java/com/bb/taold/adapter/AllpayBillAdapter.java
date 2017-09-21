package com.bb.taold.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.repay.RepayDetailActivity;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.BillInfo;
import com.bb.taold.bean.RepayDetail;
import com.bb.taold.utils.AppManager;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name AllpayBillAdapter
 */
public class AllpayBillAdapter extends ListBaseAdapter<BillInfo> {
    public AllpayBillAdapter(Context context) {
        super(context);
    }

    @Override public int getLayoutId() {
        return R.layout.item_allpay;
    }

    @Override public void onBindItemHolder(SuperViewHolder holder, int position) {
        final BillInfo billInfo = getDataList().get(position);
        RelativeLayout rl_billItem = holder.getView(R.id.rl_billitem);
        ImageView iv_status = holder.getView(R.id.iv_status);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_amount = holder.getView(R.id.tv_loanAmount);
        TextView tv_status = holder.getView(R.id.tv_status);

        rl_billItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                mBundle.putString("billId", billInfo.getId());
                AppManager.getInstance().showActivity(RepayDetailActivity.class, mBundle);
            }
        });

        //设置时间
        tv_time.setText("借款时间  " + billInfo.getLoanTime().toString());
        //设置金额
        tv_amount.setText(billInfo.getTotals().toString());
        //设置状态

        //先判断是否逾期
        if (billInfo.getIsOverdue().equals("0")) {
            //未逾期
            if (billInfo.getStatus().equals("10")) {
                tv_status.setText("还款中");
                iv_status.setImageResource(R.drawable.iv_repaying);
            } else {
                tv_status.setText("已还款");
                iv_status.setImageResource(R.drawable.icon_allpay);
            }
        } else {
            //已逾期
            tv_status.setText("已逾期");
            iv_status.setImageResource(R.drawable.iv_overdue);
        }

    }


}