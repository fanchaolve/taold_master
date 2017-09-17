package com.bb.taold.adapter;

import android.content.Context;
import android.view.View;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/16 0016
 *
 * @author chaochao
 */

public class LoanDetailStagesAdapter extends CommonRecyclerAdapter<String> {
    public LoanDetailStagesAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(MyViewHolder holder, String item) {
        if(mDatas.get(0).equals(item)){
            holder.setViewVisibility(R.id.lay_yellow_circle, View.VISIBLE)
                    .setViewVisibility(R.id.lay_gray_circle,View.GONE);
        }else{
            holder.setViewVisibility(R.id.lay_yellow_circle, View.GONE)
                    .setViewVisibility(R.id.lay_gray_circle,View.VISIBLE);
        }
    }
}
