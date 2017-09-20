package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouwan on 2017/9/20.
 */

public class EmptyView extends LinearLayout {

    @BindView(R.id.icon_image) ImageView iconImage;
    @BindView(R.id.content_textView) TextView contentTextView;
    @BindView(R.id.tv_go_loan) TextView tvGoLoan;

    public EmptyView(Context context) {
        super(context);
        initView(context, null);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loan_empty, this);
        ButterKnife.bind(view, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView);
        if (typedArray != null) {
            String emtyText = typedArray.getString(R.styleable.EmptyView_content_text);
            contentTextView.setText(emtyText);
            String btnText = typedArray.getString(R.styleable.EmptyView_btn_text);
            if (TextUtils.isEmpty(btnText)) {
                tvGoLoan.setVisibility(GONE);
            } else {
                tvGoLoan.setVisibility(GONE);
                tvGoLoan.setText(btnText);
            }
            Drawable startDrawable = typedArray.getDrawable(R.styleable.EmptyView_icon);
            if (startDrawable != null) {
                iconImage.setImageDrawable(startDrawable);
            }
            typedArray.recycle();
        }
    }

    public void setBtnLister(OnClickListener onClickListener) {
        tvGoLoan.setOnClickListener(onClickListener);
    }

    public void initViewData(String msg, boolean hasBtn) {
        tvGoLoan.setVisibility(hasBtn ? VISIBLE : GONE);
        contentTextView.setText(msg);

    }


}
