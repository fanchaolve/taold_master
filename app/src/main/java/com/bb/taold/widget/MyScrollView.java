package com.bb.taold.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/** http://blog.csdn.net/xiaanming/article/details/17374599
 * Created by Administrator on 2017/1/3.
 */

public class MyScrollView extends ScrollView{

    public OnScrollChangedListener mOnScrollChangedListener;

    public void setmOnScrollChangedListener(OnScrollChangedListener mOnScrollChangedListener) {
        this.mOnScrollChangedListener = mOnScrollChangedListener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }
}
