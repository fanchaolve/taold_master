package com.bb.taold.base.v;

import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import retrofit2.Callback;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.base.v
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/8
 * <p>
 * ==============================================
 */

public interface Frag_LoanContract {

    interface Model extends BaseModel {


        void memberInfo(Callback callback);//获取用户信息

    }

    interface View extends BaseView {
        //当前金额
        String getTotalAmount();
        //使用计算费用的id
        String getCurrentId();
        //7天id
        String get7RateId();

    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void memberInfo();

    }

}
