package com.bb.taold.widget;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bb.taold.utils.AppManager;

/**
 * Created by Administrator on 2017/1/18.
 * 虚线
 */

public class DottedLineItemDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            //绘制虚线
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setColor(0xffe3e3e3);
            //paint.setColor(parent.getContext().getColor(R.color.A6));
            Path path = new Path();
            path.moveTo(left, top);
            path.lineTo(right, top);
            int sp = AppManager.getInstance().dp2px(parent.getContext(), 3);
            PathEffect effects = new DashPathEffect(new float[]{sp, sp, sp, sp}, 1);
            paint.setPathEffect(effects);
            c.drawPath(path, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
