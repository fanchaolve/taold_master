package com.bb.taold.adapter;

import android.content.Context;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;
import com.bb.taold.bean.MessageResult;
import com.bb.taold.utils.LojaDateUtils;
import com.bb.taold.utils.StringUtils;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/14 0014
 *
 * @author chaochao
 */

public class MessagesAdapter extends CommonRecyclerAdapter<MessageResult> {
    public MessagesAdapter(Context context, List<MessageResult> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(MyViewHolder holder, MessageResult item) {
        holder.setText(R.id.tv_message_time, StringUtils.getTime(item.getGmtCreate(), LojaDateUtils.YYYY_MM_DD_DOT_FORMAT2))
                .setText(R.id.tv_message_content, item.getContent());
    }
}
