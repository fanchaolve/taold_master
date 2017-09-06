package com.bb.taold.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import com.bb.taold.activitiy.login.LoginContract;



/**
 * Created by Administrator on 2016/12/29.
 */

public class LoginTextWatcher implements TextWatcher {


    private View view;

    private LoginContract.View impl;

    public LoginTextWatcher(View view, LoginContract.View impl) {

        this.view = view;
        this.impl = impl;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
