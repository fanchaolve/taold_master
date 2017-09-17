package com.bb.taold.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.ListBaseAdapter;
import com.bb.taold.adapter.recycleradapter.SuperViewHolder;
import com.bb.taold.bean.LoanRecord;

/**
 * 类描述：
 * 创建时间：2017/9/17 0017
 *
 * @author chaochao
 */

public class LoanRecordsAdapter22 extends ListBaseAdapter<LoanRecord>{
    public LoanRecordsAdapter22(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_loan_records;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        LoanRecord item = mDataList.get(position);
        TextView titleText = holder.getView(R.id.tv_loan_days);
        titleText.setText(item.getPeriods()+"");
    }
}
