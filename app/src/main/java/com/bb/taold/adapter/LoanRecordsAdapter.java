package com.bb.taold.adapter;

import android.content.Context;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class LoanRecordsAdapter extends CommonRecyclerAdapter<String> {
    public LoanRecordsAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(MyViewHolder holder, String item) {
        holder.setText(R.id.tv_loan_days,"2888天")
              .setText(R.id.tv_loan_money,"1000.00")
        .setText(R.id.tv_loan_time,"2017/01/01 23:25");
    }
}
