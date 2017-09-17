package com.bb.taold.adapter;

import android.content.Context;
import android.view.View;

import com.bb.taold.R;
import com.bb.taold.activitiy.my.LoanDetailsActivity;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;
import com.bb.taold.bean.LoanRecord;
import com.bb.taold.utils.AppManager;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class LoanRecordsAdapter extends CommonRecyclerAdapter<LoanRecord> {
    private Context context;

    public LoanRecordsAdapter(Context context, List<LoanRecord> datas, int layoutId) {
        super(context, datas, layoutId);
        this.context = context;
    }

    @Override
    public void convert(MyViewHolder holder, LoanRecord loanRecord) {
        holder.setText(R.id.tv_loan_days,loanRecord.getPeriods()+"天")
              .setText(R.id.tv_loan_money,loanRecord.getLoanAmount())
        .setText(R.id.tv_loan_time,loanRecord.getApplyTime()+"")
        .setOnIntemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getInstance().showActivity(LoanDetailsActivity.class,null);
            }
        });

    }
}
