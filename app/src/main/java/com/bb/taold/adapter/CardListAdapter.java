package com.bb.taold.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.cardList.CardListActivity;
import com.bb.taold.bean.CardInfo;
import com.bb.taold.widget.SwipeListLayout;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/12.
 * Package Name com.bb.taold.adapter
 * Class Name CardListAdapter
 */
public class CardListAdapter extends BaseAdapter {

    public CardListActivity mActivity;
    public ArrayList<CardInfo> list;
    public ViewHolder holder;

    public CardListAdapter(CardListActivity mActivity,ArrayList<CardInfo> list){
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

        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_card, null);
            holder = new ViewHolder();

            holder.sll_main = (SwipeListLayout)convertView.findViewById(R.id.sll_main);
            holder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            holder.sll_main.setOnSwipeStatusListener(new CardListActivity.MyOnSlipStatusListener(
                    holder.sll_main));
            holder.tv_delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    holder.sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    mActivity.removeCard(list.get(position).getId());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            //银行图标
            holder.iv_bankicon = (ImageView) convertView.findViewById(R.id.iv_bankicon);
            //银行名称
            holder.tv_bankname = (TextView) convertView.findViewById(R.id.tv_bankname);
            //卡片尾号+卡片类型
            holder.tv_cardinfo = (TextView) convertView.findViewById(R.id.tv_cardinfo);
            //还款卡或是扣款卡
            holder.tv_cardtype = (TextView) convertView.findViewById(R.id.tv_cardtype);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_bankname.setText(list.get(position).getCardName());
        String cardNo = list.get(position).getCardno();
        if(cardNo.length()>4){
            cardNo = cardNo.substring(cardNo.length()-4,cardNo.length());
        }
        holder.tv_cardinfo.setText("尾号"+cardNo+" "+"借记卡");
        holder.tv_cardtype.setText("借款卡");

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_bankicon;
        TextView tv_bankname;
        TextView tv_cardinfo;
        TextView tv_cardtype;
        TextView tv_delete;
        SwipeListLayout sll_main;
    }

}