package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
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
 * ==============================================
 * <p>
 * 包名：com.bb.taold.widget
 * <p>
 * 说明：水平表单
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/10
 * <p>
 * ==============================================
 */

public class Line_ItemView extends LinearLayout {


    private String hint = "";
    private Context mContext;
    private String left_name = "";//左边的标题

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.et_value)
    TextView et_value;

    @BindView(R.id.iv_last)
    ImageView iv_last;


    public Line_ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Line_ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Line_ItemView, defStyleAttr, 0);
        left_name = a.getString(R.styleable.Line_ItemView_left_name);
        hint = a.getString(R.styleable.Line_ItemView_hint);
        a.recycle();
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.line_item, this, false);
        addView(view);
        ButterKnife.bind(this);

        tv_name.setText(left_name);
        et_value.setHint(hint);
    }

    public void setInputType() {
        et_value.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    public String getValue() {
        return et_value.getText().toString().trim();
    }

    public void select_View(){
        et_value.setEnabled(false);
        et_value.setFocusable(false);
        et_value.setFocusableInTouchMode(false);
        iv_last.setVisibility(View.VISIBLE);
    }

    public void setText(String text){
        et_value.setText(text);
    }

}
