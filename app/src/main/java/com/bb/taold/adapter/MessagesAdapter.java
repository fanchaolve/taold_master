package com.bb.taold.adapter;

import android.content.Context;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/14 0014
 *
 * @author chaochao
 */

public class MessagesAdapter extends CommonRecyclerAdapter<String> {
    public MessagesAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(MyViewHolder holder, String item) {
        holder.setText(R.id.tv_message_time,"2017.03.20 17:59")
                .setText(R.id.tv_message_content,"hi\n欢迎来到\n如果有\n拨打4008821234")
                .setText(R.id.tv_message_time,"2017/01/01 23:25");
    }
}
