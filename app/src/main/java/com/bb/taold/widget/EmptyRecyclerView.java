package com.bb.taold.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fancl.
 * 数据为空时展示emptyView
 */

public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;


    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {

            Adapter adapter = getAdapter();
            if (adapter.getItemCount() == 0) {
                emptyView.setVisibility(View.VISIBLE);
                EmptyRecyclerView.this.setVisibility(View.GONE);
            } else {
                emptyView.setVisibility(View.GONE);
                EmptyRecyclerView.this.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            onChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            onChanged();
        }
    };


    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        ((ViewGroup) this.getRootView()).addView(emptyView);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(mObserver);
        mObserver.onChanged();
    }
}
