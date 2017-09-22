package com.bb.taold.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.cardList.CardListActivity;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.CardInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * 类描述：
 * 创建时间：2017/9/22 0022
 *
 * @author chaochao
 */

public class SwiperMenuAdapter extends ListBaseAdapter<CardInfo>{
    private  CardListActivity mActivity;

    public SwiperMenuAdapter(Context context) {
        super(context);
        mActivity = (CardListActivity)context;
    }

    @Override public int getLayoutId() {
        return R.layout.item_card;
    }

    @Override public void onBindItemHolder(SuperViewHolder holder, final int position) {
        View view = holder.getView(R.id.swiper_content);
        TextView tv_delete = holder.getView(R.id.tv_delete);
        //银行图标
        ImageView iv_bankicon = holder.getView(R.id.iv_bankicon);
        //银行名称
        TextView tv_bankname = holder.getView(R.id.tv_bankname);
        //卡片尾号+卡片类型
        TextView tv_cardinfo = holder.getView(R.id.tv_cardinfo);
        //还款卡或是扣款卡
        TextView tv_cardtype = holder.getView(R.id.tv_cardtype);

        tv_bankname.setText(mDataList.get(position).getCardName());
        String cardNo = mDataList.get(position).getCardno();
        if(cardNo.length()>4){
            cardNo = cardNo.substring(cardNo.length()-4,cardNo.length());
        }
        tv_cardinfo.setText("尾号"+cardNo+" "+"借记卡???");
        tv_cardtype.setText("借款卡????");
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mActivity.removeCard(mDataList.get(position).getId());
                notifyDataSetChanged();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                CardInfo cardInfo = mDataList.get(position);
                if (cardInfo != null) {
                    EventBus.getDefault().post(cardInfo);
                    ((Activity)mContext).finish();
                }
            }
        });
    }
}
