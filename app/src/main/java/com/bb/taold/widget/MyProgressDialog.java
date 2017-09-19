package com.bb.taold.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;


/**
 *
 */

public class MyProgressDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    public MyProgressDialog(Context context, String content, int id) {
        super(context, R.style.CommProgressDialog);
        this.mContext = context;
        this.mLoadingTip = content;
        this.mResid = id;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        mLoadingTv.setText(mLoadingTip);

    }

    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

    private void initView() {
        setContentView(R.layout.layout_toast_tip);
        mLoadingTv = (TextView) findViewById(R.id.tvTip);
        mImageView = (ImageView) findViewById(R.id.ivStatus);
        LinearLayout llContent = (LinearLayout) findViewById(R.id.llContent);
        //换算比例
        double scale = MyApplication.widthPixels/750.0;
        llContent.setLayoutParams(new LinearLayout.LayoutParams((int)(scale * 200 + 0.5),(int)(scale * 200 + 0.5)));
    }

}
