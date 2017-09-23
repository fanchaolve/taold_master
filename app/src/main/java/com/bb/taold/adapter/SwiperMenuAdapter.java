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
import com.bb.taold.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
        final CardInfo cardInfo = mDataList.get(position);
        View view = holder.getView(R.id.swiper_content);
        TextView tv_delete = holder.getView(R.id.tv_delete);
        //银行图标
        ImageView iv_bankicon = holder.getView(R.id.iv_bankicon);
        Glide.with(mContext)
                .load(cardInfo.getCardImg())
                .error(R.drawable.card_default)
                .crossFade()
                .centerCrop()
                .priority(Priority.NORMAL) //下载的优先级
                //all:缓存源资源和转换后的资源 none:不作任何磁盘缓存
                //source:缓存源资源   result：缓存转换后的资源
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .into(iv_bankicon);
        //银行名称
        TextView tv_bankname = holder.getView(R.id.tv_bankname);
        //卡片尾号+卡片类型
        TextView tv_cardinfo = holder.getView(R.id.tv_cardinfo);
        //还款卡或是扣款卡
        TextView tv_cardtype = holder.getView(R.id.tv_cardtype);

        tv_bankname.setText(cardInfo.getCardName());
        String cardNo = cardInfo.getCardno();
        if(cardNo.length()>4){
            cardNo = cardNo.substring(cardNo.length()-4,cardNo.length());
        }
        if(cardInfo.getCardType().equals(Constants.CARD_TYPE_DEBIT)){
            tv_cardinfo.setText("尾号"+cardNo+" "+"借记卡");
        }else if(cardInfo.getCardType().equals(Constants.CARD_TYPE_CREDIT)){
            tv_cardinfo.setText("尾号"+cardNo+" "+"信用卡");
        }
//        tv_cardtype.setText("借款卡????");
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mActivity.removeCard(cardInfo.getId());
                notifyDataSetChanged();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (cardInfo != null) {
                    EventBus.getDefault().post(cardInfo);
                    ((Activity)mContext).finish();
                }
            }
        });
    }
}
