package com.bb.taold.base.v;

import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface HomeContract {


    interface Model extends BaseModel {




    }


    interface View extends BaseView {



    }

    abstract class Presenter extends BasePresenter<HomeContract.Model, HomeContract.View> {




    }
}
