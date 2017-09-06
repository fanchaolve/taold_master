package com.bb.taold.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bb.taold.R;


/**
 * Created by Administrator on 2017/2/20.
 */

public class FancyCountDownTextview extends TextView {


    private Context mContext;

    private long time = 1800000;

    public FancyCountDownTextview(Context context) {
        this(context, null, 0);
    }

    public FancyCountDownTextview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FancyCountDownTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    private CountDownTimer mCountDownTimer = new CountDownTimer(time, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) millisUntilFinished / 1000;
            int hour = progress / 3600;
            int min = (progress - 3600 * hour) / 60;
            int second = (progress - 3600 * hour) % 60;
            String h = String.format("%02d", hour);
            String m = String.format("%02d", min);
            String s = String.format("%02d", second);
            String fomart = getResources().getString(R.string.confimpay1, h, m, s);
            MySpannableString spannableString = new MySpannableString(fomart);
            setText(spannableString);


        }

        @Override
        public void onFinish() {
            setText("订单已经失效");
            //CountDownTextView.this.setEnabled(true);
            //CountDownTextView.this.setText(beforeStr);
        }
    };

    public class MySpannableString extends SpannableString {

        public MySpannableString(CharSequence source) {
            super(source);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A3_Size12), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A99_Size12), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A3_Size12), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A99_Size12), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A3_Size12), 7, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A99_Size12), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setSpan(new TextAppearanceSpan(
                    mContext, R.style.TextAppear_Theme_A3_Size12), 10, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        @Override
        public void setSpan(Object what, int start, int end, int flags) {
            super.setSpan(what, start, end, flags);
        }
    }

    public void startTime() {
        mCountDownTimer.start();

    }


    public void cancelTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }

    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
