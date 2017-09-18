package com.bb.taold.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.LifeCycleBean;

/**
 * 类描述：
 * 创建时间：2017/9/17 0017
 *
 * @author chaochao
 */

public class RecordStageAdapter extends ListBaseAdapter<LifeCycleBean> {
    public RecordStageAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_loan_stages;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView tvStageName = holder.getView(R.id.tv_stage_name);
        TextView tvStageTime = holder.getView(R.id.tv_stage_time);
        TextView tvStagePrompt = holder.getView(R.id.tv_stage_prompt);
        LinearLayout layGrayCicle = holder.getView(R.id.lay_gray_circle);
        LinearLayout layYellowCicle = holder.getView(R.id.lay_yellow_circle);
        LifeCycleBean lifeCycleBean = mDataList.get(position);
        tvStageName.setText(lifeCycleBean.getTitle());
        tvStageTime.setText(lifeCycleBean.getDate()+"");
        tvStagePrompt.setText(lifeCycleBean.getDescription());
        layGrayCicle.setVisibility(View.GONE);
        layYellowCicle.setVisibility(View.GONE);
        if(position==0){
            layYellowCicle.setVisibility(View.VISIBLE);
            tvStageName.setTextColor(mContext.getResources().getColor(R.color.btn_normal));
        }else{
            layGrayCicle.setVisibility(View.VISIBLE);
            tvStageName.setTextColor(mContext.getResources().getColor(R.color.color_important));
        }
    }
}
