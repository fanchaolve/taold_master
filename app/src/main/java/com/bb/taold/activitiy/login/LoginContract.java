package com.bb.taold.activitiy.login;

import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import com.bb.taold.base.v.BaseView;

import retrofit2.Callback;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface LoginContract {


    interface Model extends BaseModel {
        void login(String name, String pass,String appkey, Callback callback);

    }


    interface View  extends BaseView{

        void loginSuccess();


        boolean isverTel(String userPhone);




    }

    abstract class Presenter extends BasePresenter<Model, View> {


        public abstract void login(String name, String pass);



        public void regist(String tel,String code,String psw){

        }

        //找回密码
        public  void getPsw(String tel,String code){

        }

        @Override
        public void onAttached() {}
    }
}
