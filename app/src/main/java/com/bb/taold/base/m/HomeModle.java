package com.bb.taold.base.m;

import com.bb.taold.api.ApiService;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.v.HomeContract;

/**
 * Created by Administrator on 2017/1/9.
 */

public class HomeModle implements HomeContract.Model {


    private ApiService service;

    public HomeModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }




}
