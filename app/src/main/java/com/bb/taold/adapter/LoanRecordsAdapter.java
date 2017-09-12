package com.bb.taold.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bb.taold.R;
import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;
import com.bb.taold.adapter.recycleradapter.HolderImageLoader;
import com.bumptech.glide.Glide;

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
        .setImageByUrl(R.id.iv_test,new HolderImageLoader("https://www.baidu.com/img/bd_logo1.png"){

            @Override
            public void displayImage(Context context, ImageView imageView, String imagePath) {
                Glide.with(mContext)
                        .load(imagePath)
                        .into(imageView);
            }
        });
    }
}
