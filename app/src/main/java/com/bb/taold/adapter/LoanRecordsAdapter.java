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
        holder.setText(R.id.tv_value,item)
              .setImageByUrlByTool(R.id.iv_test,"https://www.baidu.com/img/bd_logo1.png");
    }
}
