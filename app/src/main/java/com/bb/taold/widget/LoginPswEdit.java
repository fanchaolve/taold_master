package com.bb.taold.widget;

import android.content.Context;
import android.content.res.TypedArray;

import android.graphics.drawable.Drawable;

import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bb.taold.R;


/**
 * Created by fancl.
 */

public class LoginPswEdit extends EditText {

    public Drawable startDrawable;

    public Drawable edit_lookDrawable;

    public Drawable edit_unLookDrawable;

    private boolean isLook = false;//是否可见

    private Context context;

    private String hintString;

    public void setHintString(String hintString) {
        this.hintString = hintString;
        setHint(this.hintString);
    }

    public LoginPswEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LoginPswEdit, defStyleAttr, 0);
        startDrawable = a.getDrawable(R.styleable.LoginPswEdit_edit_Drawable);
        edit_lookDrawable = a.getDrawable(R.styleable.LoginPswEdit_edit_lookDrawable);
        edit_unLookDrawable = a.getDrawable(R.styleable.LoginPswEdit_edit_unLookDrawable);
        hintString=getResources().getString(R.string.passworddetail);
        a.recycle();
        initView();
    }

    public LoginPswEdit(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);

    }


    private void initView() {
        if (startDrawable != null) {
            startDrawable.setBounds(0, 0, startDrawable.getMinimumWidth(), startDrawable.getMinimumHeight());//对图片进行压缩
        }

        if (edit_lookDrawable != null) {
            edit_lookDrawable.setBounds(0, 0, edit_lookDrawable.getMinimumWidth(), edit_lookDrawable.getMinimumHeight());//对图片进行压缩
        }

        if (edit_unLookDrawable != null) {
            edit_unLookDrawable.setBounds(0, 0, edit_unLookDrawable.getMinimumWidth(), edit_unLookDrawable.getMinimumHeight());//对图片进行压缩
        }

        if (isLook) {
            LoginPswEdit.this.setCompoundDrawables(startDrawable, null, edit_lookDrawable, null);
            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            LoginPswEdit.this.setCompoundDrawables(startDrawable, null, edit_unLookDrawable, null);
            setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }


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


                    isLook = !isLook;
                    if (isLook) {
                        LoginPswEdit.this.setCompoundDrawables(startDrawable, null, edit_lookDrawable, null);
                        setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    } else {
                        LoginPswEdit.this.setCompoundDrawables(startDrawable, null, edit_unLookDrawable, null);
                        setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    }

                    int  index=0;
                    if(!TextUtils.isEmpty(LoginPswEdit.this.getText())){
                        index=LoginPswEdit.this.getText().toString().trim().length();
                    }
                    setSelection(index);

                    return false;
                }

                return false;
            }
        });

        SpannableString hintSp = new SpannableString(hintString);
        hintSp.setSpan(new TextAppearanceSpan(
                context, R.style.TextAppear_Theme_Size14), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hintSp.setSpan(new TextAppearanceSpan(context, R.style.TextAppear_Theme_Size12), 5, 19,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        hintSp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.A5)), 0, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
        setHint(hintSp);

    }


}
