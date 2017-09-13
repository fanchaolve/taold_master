package com.bb.taold.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.bean.RepayDetail;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name AllpayBillAdapter
 */
public class AllpayBillAdapter extends BaseAdapter {

    public Activity mActivity;
    public ArrayList<RepayDetail> list;

    public AllpayBillAdapter(Activity mActivity, ArrayList<RepayDetail> list){
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

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置时间
        holder.tv_time.setText(list.get(position).getTime().toString());
        //设置金额
        holder.tv_amount.setText(list.get(position).getAmount().toString());
        //设置状态
        holder.tv_status.setText(list.get(position).getStatus().toString());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_time;
        TextView tv_amount;
        TextView tv_status;
    }

}