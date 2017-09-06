package com.bb.taold.base.v;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface BaseView {

    void showMsg(String msg);

    void initLoading();

    void showLoading();

    void dissmissLoading();

    void updateLoading();
}
