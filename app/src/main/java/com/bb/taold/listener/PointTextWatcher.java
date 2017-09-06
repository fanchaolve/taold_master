package com.bb.taold.interfaces;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.bb.taold.R;

/**
 * Created by fancl.
 * 监听只允许小数点后两位
 */

public class PointTextWatcher implements TextWatcher {


    private EditText editText;

    private View view;

    public PointTextWatcher(EditText editText) {
        this.editText = editText;
    }

    public PointTextWatcher(EditText editText, View view) {
        this.editText = editText;
        this.view = view;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s))
            return;
        if (s.toString().contains(".")) {//有小数点 就控制长度
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }

        if (s.toString().trim().charAt(0) == ('.')) {//有小数点 在第一位 就控制
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }

        if (Float.valueOf(s.toString()) > 0) {
            if (view != null) {
                view.setBackgroundResource(R.drawable.shape_btn_a1);
            }
        } else {
            if (view != null) {
                view.setBackgroundResource(R.drawable.shape_btn_a6);
            }
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
