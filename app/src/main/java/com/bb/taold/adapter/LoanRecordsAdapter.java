package com.bb.taold.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.LoanRecord;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.StringUtils;

/**
 * 类描述：
 * 创建时间：2017/9/17 0017
 *
 * @author chaochao
 */

public class LoanRecordsAdapter extends ListBaseAdapter<LoanRecord>{
    public LoanRecordsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_loan_records;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        LoanRecord item = mDataList.get(position);
        TextView tvLoanDays = holder.getView(R.id.tv_loan_days);
        TextView tvLoanMoney = holder.getView(R.id.tv_loan_money);
        TextView tvLoanTime = holder.getView(R.id.tv_loan_time);
        tvLoanDays.setText(item.getPeriods()+"");
        tvLoanMoney.setText(item.getLoanAmount()+"");
        tvLoanTime.setText(StringUtils.getTime(item.getApplyTime(), Constants.yyyyMMddHHmm));
    }
}
