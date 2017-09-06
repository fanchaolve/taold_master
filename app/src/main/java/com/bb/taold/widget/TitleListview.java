package com.bb.taold.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

import java.lang.ref.WeakReference;

/**
 * 自动滚动信息
 * Created by fancl
 */

public class TitleListview extends ListView {


    private final static String TAG = TitleListview.class.getSimpleName();

    private int height = 100;//给定列表的高度固定

    private int mPosition = -1;//按下时的item

    private int count = 1;//自动滚动下标


    private TimeTaskScroll timeTaskScroll;


    public TitleListview(Context context) {
        super(context);
        initdata();
    }


    public TitleListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initdata();
    }

    public TitleListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initdata();
    }

    private void initdata() {

        timeTaskScroll = new TimeTaskScroll(this);
        //height = MyApplication.dp2px(getContext(), 50);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(widthMeasureSpec, height);

    }


    @Override
    public void setAdapter(android.widget.ListAdapter adapter) {
        super.setAdapter(adapter);
        Log.i(TAG, "setAdapter");
        startTimeScoll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
        stopTimeScoll();
        count = 1;

    }


    //开始滚动
    public void startTimeScoll() {
        stopTimeScoll();
        postDelayed(timeTaskScroll, 3000);

    }

    //结束滚动
    public void stopTimeScoll() {
        removeCallbacks(timeTaskScroll);

    }

//    @Override
//    protected void onVisibilityChanged(View changedView, int visibility) {
//        super.onVisibilityChanged(changedView, visibility);
//        if (visibility == VISIBLE) {
//            startTimeScoll();
//        } else if (visibility == INVISIBLE) {
//            stopTimeScoll();
//        }
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopTimeScoll();
                // 记录手指按下时的位置
                mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startTimeScoll();
                if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {
                    return super.dispatchTouchEvent(ev);
                } else {
                    setPressed(false);
                    invalidate();
                    return true;
                }
            default:
                break;

        }

        return super.dispatchTouchEvent(ev);
    }

    public class TimeTaskScroll implements Runnable {

        private final WeakReference<TitleListview> mTitleListview;


        private TimeTaskScroll(TitleListview mTitleListview) {
            this.mTitleListview = new WeakReference<>(mTitleListview);

        }

        @Override
        public void run() {
            TitleListview view = mTitleListview.get();
            if (view != null) {
//                Log.i(TAG, count + "");
                view.smoothScrollToPositionFromTop(count++, 0, 200);
                view.startTimeScoll();
            }
        }
    }
}
