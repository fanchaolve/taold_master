package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bb.taold.R;

/**
 * Created by fancl.
 */

public class LoginTelEdit extends EditText {

    public Drawable startDrawable;

    public Drawable edit_endDrawable;


    public LoginTelEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LoginTelEdit, defStyleAttr, 0);
        startDrawable = a.getDrawable(R.styleable.LoginTelEdit_edit_startDrawable);
        edit_endDrawable = a.getDrawable(R.styleable.LoginTelEdit_edit_endDrawable);
        a.recycle();
        initView();
    }

    public LoginTelEdit(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);

    }


    private void initView() {

        if (startDrawable != null) {
            startDrawable.setBounds(0, 0, startDrawable.getMinimumWidth(), startDrawable.getMinimumHeight());//对图片进行压缩
        }

        if (edit_endDrawable != null) {
            edit_endDrawable.setBounds(0, 0, edit_endDrawable.getMinimumWidth(), edit_endDrawable.getMinimumHeight());//对图片进行压缩
        }

        LoginTelEdit.this.setCompoundDrawables(startDrawable, null, null, null);

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    LoginTelEdit.this.setCompoundDrawables(startDrawable, null, null, null);
                } else {
                    LoginTelEdit.this.setCompoundDrawables(startDrawable, null, edit_endDrawable, null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = ((EditText) v).getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > ((EditText) v).getWidth()
                        - ((EditText) v).getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    ((EditText) v).setText("");

                    return false;
                }

                return false;
            }
        });


    }


    public Drawable getStartDrawable() {
        return startDrawable;
    }

    public void setStartDrawable(Drawable startDrawable) {
        this.startDrawable = startDrawable;
    }

    public Drawable getEdit_endDrawable() {
        return edit_endDrawable;
    }

    public void setEdit_endDrawable(Drawable edit_endDrawable) {
        this.edit_endDrawable = edit_endDrawable;
    }


}
