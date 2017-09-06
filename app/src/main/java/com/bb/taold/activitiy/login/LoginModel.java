package com.bb.taold.activitiy.login;

import android.util.Log;

import com.bb.taold.api.ApiService;
import com.bb.taold.api.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/5.
 */

public class LoginModel implements LoginContract.Model {

    private ApiService service;


    public LoginModel() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }

    @Override
    public void login(String name, String pass,String appkey, Callback callback) {
        Call call =service.login(name,pass,appkey);
        call.enqueue(callback);

    }


}
