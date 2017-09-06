package com.bb.taold.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bb.taold.R;


/**
 * Created by fancl on 2016/12/29.
 * 倒计时的textview
 */

public class CountDownTextView extends TextView {


    private final String TAG = CountDownTextView.class.getSimpleName();

    private String beforeStr;//点击之前的

    private Context mContext;

    public CountDownTextView(Context context) {
        this(context, null, 0);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (TextUtils.isEmpty(this.getText())) {
            beforeStr = this.getText().toString();
        } else {
            beforeStr = getResources().getString(R.string.getVerificationCode);
        }

        initdate();
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private void initdate() {
//
//        Log.i(TAG, "initdate()");
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startTime();
//
//
//            }
//        });
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) millisUntilFinished / 1000;
            CountDownTextView.this.setText(mContext.getString(R.string.processTime, progress + ""));

        }

        @Override
        public void onFinish() {
            //mView.endTime();
            CountDownTextView.this.setEnabled(true);
            CountDownTextView.this.setText(beforeStr);
        }
    };


    public void startTime() {
        mCountDownTimer.start();
        CountDownTextView.this.setEnabled(false);
    }


    public void cancelTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        CountDownTextView.this.setEnabled(true);
        CountDownTextView.this.setText(beforeStr);
    }


}
