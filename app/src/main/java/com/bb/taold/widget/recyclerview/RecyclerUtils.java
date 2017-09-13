package com.bb.taold.widget.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bb.taold.adapter.recycleradapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/13 0013
 *
 * @author chaochao
 */

public abstract class RecyclerUtils<T> {
    private List<T> datas;

    /**
     *
     * @param mContext
     * @param datas 数据集合
     * @param recyclerview
     * @param adapter CommonRecyclerAdapter子类
     * @param swiper SwipeRefreshLayout
     */
    public RecyclerUtils(final Context mContext, final List<T> datas, RecyclerView recyclerview, final CommonRecyclerAdapter adapter, final SwipeRefreshLayout swiper){
        this.datas = datas;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);
        recyclerview.addOnScrollListener(new LoadMoreImpl(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                RecyclerUtils.this.onLoadMore();
            }
        });
        swiper.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RecyclerUtils.this.onRefresh();
                swiper.setRefreshing(false);
            }
        });
    }

    public abstract void onLoadMore();
    public abstract void onRefresh();


}
