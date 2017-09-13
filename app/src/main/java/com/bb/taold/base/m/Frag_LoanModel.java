package com.bb.taold.base.m;

import com.bb.taold.api.ApiService;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.v.Frag_LoanContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.base.m
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/8
 * <p>
 * ==============================================
 */

public class Frag_LoanModel implements Frag_LoanContract.Model  {

    private ApiService service;

    public Frag_LoanModel(){
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }

    @Override
    public void memberInfo(Callback callback) {
        Call call = service.member_identityAuthInfo();
        call.enqueue(callback);
    }

}
