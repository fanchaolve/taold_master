package com.bb.taold.activitiy.login;

import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;

/**
 * Created by Administrator on 2016/12/5.
 */

public class LoginPresenter extends LoginContract.Presenter {



    private PostCallback postCallback=new PostCallback<LoginContract.View>(mView) {
        @Override
        public void successCallback(Result_Api api) {


        }

        @Override
        public void failCallback() {

        }
    };

    @Override
    public void login(String name, String pass) {
        mModel.login(name,pass,"II",postCallback);
    }


}
