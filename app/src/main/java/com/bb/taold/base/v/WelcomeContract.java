package com.bb.taold.base.v;

import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import com.bb.taold.bean.User;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface WelcomeContract {


    interface Model extends BaseModel {

        void getClientCtlInfo(String userId, Callback callback);

        User settingUser();

    }


    interface View extends BaseView {

        void settingUser(User user);


    }

    abstract class Presenter extends BasePresenter<WelcomeContract.Model, WelcomeContract.View> {

        public abstract void settingUser();

        public abstract void getClientCtlInfo(String userId);
    }
}
