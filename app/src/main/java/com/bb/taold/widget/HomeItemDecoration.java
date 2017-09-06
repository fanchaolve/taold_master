package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bb.taold.widget.multitype.MultiTypeAdapter;
import com.bb.taold.widget.multitype.data.Item;

/**
 * Created by Administrator on 2016/12/14.
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration {



    private Context mContext;

    private int mOrientation;

    private Drawable mDivider;

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS  = new int[]{
            android.R.attr.listDivider
    };



    public HomeItemDecoration(Context context) {
        this.mContext = context;
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();

    }




//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw();
//       drawHorizontalLine(c, parent, state);
//
//    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontalLine(c, parent, state);
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() instanceof MultiTypeAdapter) {

        } else {
            throw new IllegalArgumentException("转换异常");
        }

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            Item item = (Item) ((MultiTypeAdapter) parent.getAdapter()).getItems().get(i);
//            if (item instanceof ProductListBean) {
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
//            }


        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());

    }
}
