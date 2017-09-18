package com.bb.taold.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.cardList.CardListActivity;
import com.bb.taold.activitiy.repay.RepayInfoActivity;
import com.bb.taold.bean.BillInfoDetail;
import com.bb.taold.bean.BillItem;
import com.bb.taold.bean.BillProductInfo;
import com.bb.taold.bean.RepayDetail;
import com.bb.taold.utils.AppManager;
import com.bb.taold.widget.SwipeListLayout;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name CardListAdapter
 */
public class RepayDetailAdapter extends BaseAdapter {

    public Activity mActivity;
    public ArrayList<BillItem> list;
    public BillProductInfo productInfo;

    public RepayDetailAdapter(Activity mActivity, BillInfoDetail info){
        this.mActivity = mActivity;
        this.list = info.getBillItems();
        this.productInfo = info.getProductInfo();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_repay_detail, null);
            holder = new ViewHolder();
            holder.rl_detail = (RelativeLayout) convertView.findViewById(R.id.rl_item_detail);
            //分期期数
            holder.tv_period = (TextView) convertView.findViewById(R.id.tv_period);
            //分期还款时间
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            //应还金额
            holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_loanAmount);
            //当前状态
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置期数
        holder.tv_period.setText(list.get(position).getStages().toString()+"/"+productInfo.getTotalStages()+"期");
        //设置时间
        holder.tv_time.setText(list.get(position).getRepayDate().toString());
        //设置金额
        holder.tv_amount.setText(list.get(position).getAmtMoney().toString());
        //设置状态
        if(list.get(position).getIsOverdue().equals("true")){
            holder.tv_status.setText("已逾期");
            holder.rl_detail.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
        }else{
            if(list.get(position).getStatus().equals("10")){
                holder.tv_status.setText("待还款");
                holder.rl_detail.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
            }else{
                holder.tv_status.setText("已还清");
            }
        }
        holder.rl_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                mBundle.putString("billItemId",list.get(position).getBillItemId());
                //跳转判断
                if(list.get(position).getIsOverdue().equals("true")){
                    //逾期
                    mBundle.putString("bill_status","2");
                }else{
                    if(list.get(position).getStatus().equals("10")){
                        mBundle.putString("bill_status","0");
                    }else{
                        mBundle.putString("bill_status","1");
                    }
                }
                AppManager.getInstance().showActivity(RepayInfoActivity.class,mBundle);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        RelativeLayout rl_detail;
        TextView tv_period;
        TextView tv_time;
        TextView tv_amount;
        TextView tv_status;

    }

}