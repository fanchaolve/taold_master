package com.bb.taold.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.bean.BillInfo;
import com.bb.taold.bean.RepayDetail;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name AllpayBillAdapter
 */
public class AllpayBillAdapter extends BaseAdapter {

    public Activity mActivity;
    public ArrayList<BillInfo> list;

    public AllpayBillAdapter(Activity mActivity, ArrayList<BillInfo> list){
        this.mActivity = mActivity;
        this.list = list;
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
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_allpay, null);
            holder = new ViewHolder();
            //分期还款时间
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            //应还金额
            holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_loanAmount);
            //当前状态
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            //当前状态图标
            holder.iv_status = (ImageView) convertView.findViewById(R.id.iv_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置时间
        holder.tv_time.setText("借款时间  "+list.get(position).getLoanTime().toString());
        //设置金额
        holder.tv_amount.setText(list.get(position).getTotals().toString());
        //设置状态

        //先判断是否逾期
        if(list.get(position).getIsOverdue().equals("0")){
            //未逾期
            if(list.get(position).getStatus().equals("10")){
                holder.tv_status.setText("还款中");
                holder.iv_status.setImageResource(R.drawable.iv_repaying);
            }else{
                holder.tv_status.setText("已还款");
                holder.iv_status.setImageResource(R.drawable.icon_allpay);
            }
        }else{
            //已逾期
            holder.tv_status.setText("已逾期");
            holder.iv_status.setImageResource(R.drawable.iv_overdue);
        }


        return convertView;
    }

    static class ViewHolder {
        ImageView iv_status;
        TextView tv_time;
        TextView tv_amount;
        TextView tv_status;
    }

}