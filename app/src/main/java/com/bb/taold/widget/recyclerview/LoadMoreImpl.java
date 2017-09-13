package com.bb.taold.widget.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 类描述：
 * 创建时间：2017/8/14
 *
 * @author chaochao
 */

public abstract class LoadMoreImpl extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLayoutManager;
    private int firstVisibleItem, visibleItemCount, totalItemCount;
    public LoadMoreImpl(LinearLayoutManager layoutManager){
        mLayoutManager = layoutManager;
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        if(firstVisibleItem+visibleItemCount>=totalItemCount){
            onLoadMore();
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    public abstract void onLoadMore();
}
