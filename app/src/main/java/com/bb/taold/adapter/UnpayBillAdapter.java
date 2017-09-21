package com.bb.taold.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.repay.RepayInfoActivity;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.BillItem;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.LojaDateUtils;
import com.bb.taold.utils.StringUtils;

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
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_period = holder.getView(R.id.tv_period);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_amount = holder.getView(R.id.tv_loanAmount);
        //设置期数
        tv_period.setText(billItem.getStages().toString() + "/" + stages + "期");
        //设置时间
        tv_time.setText(StringUtils.getTime(billItem.getRepayDate(), LojaDateUtils.YYYY_MM_DD_DOT_FORMAT));
        //设置金额
        tv_amount.setText(billItem.getAmtMoney().toString());
        //设置状态
        tv_status.setText(billItem.getOverdueStatusDesc());
        //还款按钮
        holder.getView(R.id.tv_paybtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                if (billItem.isOverdue) {
                    mBundle.putString("bill_status", "2");
                } else {
                    mBundle.putString("bill_status", "0");
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