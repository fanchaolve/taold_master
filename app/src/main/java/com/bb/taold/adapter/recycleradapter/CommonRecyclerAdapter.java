package com.bb.taold.adapter.recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;
    private int mLayoutId;

    public CommonRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 先inflate数据
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        // 返回ViewHolder
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((MyViewHolder)holder, mDatas.get(position));
    }

    /**
     * @param item 当前的数据
     */
    public abstract void convert(MyViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // 用来存放子View减少findViewById的次数
        private SparseArray<View> mViews;

        public MyViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }

        /**
         * 设置TextView文本
         */
        public MyViewHolder setText(int viewId, CharSequence text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        /**
         * 通过id获取view
         */
        public <T extends View> T getView(int viewId) {
            // 先从缓存中找
            View view = mViews.get(viewId);
            if (view == null) {
                // 直接从ItemView中找
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 设置View的Visibility
         */
        public MyViewHolder setViewVisibility(int viewId, int visibility) {
            getView(viewId).setVisibility(visibility);
            return this;
        }

        /**
         * 设置ImageView的资源
         */
        public MyViewHolder setImageResource(int viewId, int resourceId) {
            ImageView imageView = getView(viewId);
            imageView.setImageResource(resourceId);
            return this;
        }

        /**
         * 设置条目点击事件
         */
        public void setOnIntemClickListener(View.OnClickListener listener) {
            itemView.setOnClickListener(listener);
        }

        /**
         * 设置条目长按事件
         */
        public void setOnIntemLongClickListener(View.OnLongClickListener listener) {
            itemView.setOnLongClickListener(listener);
        }

        /**
         * 设置图片通过路径,这里稍微处理得复杂一些，因为考虑加载图片的第三方可能不太一样
         * 也可以直接写死 eg:setImageByUrlByTool
         */
        public MyViewHolder setImageByUrl(int viewId, HolderImageLoader imageLoader) {
            ImageView imageView = getView(viewId);
            if (imageLoader == null) {
                throw new NullPointerException("imageLoader is null!");
            }
            imageLoader.displayImage(imageView.getContext(), imageView, imageLoader.getImagePath());
            return this;
        }

        /**
         * 通过三方工具来获取图片(方便替换工具)
         * @param viewId
         * @param url
         */
        public MyViewHolder setImageByUrlByTool(int viewId, String url) {
            ImageView imageView = getView(viewId);
            Glide.with(mContext).load(url).into(imageView);
            return this;
        }


    }
}