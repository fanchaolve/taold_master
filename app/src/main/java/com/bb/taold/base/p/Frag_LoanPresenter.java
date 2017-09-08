package com.bb.taold.base.p;

import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.v.Frag_LoanContract;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.base.p
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/8
 * <p>
 * ==============================================
 */

public class Frag_LoanPresenter extends Frag_LoanContract.Presenter {

    //callback 回调接受
    private PostCallback postCallback =new PostCallback<Frag_LoanContract.View>(mView) {
        @Override
        public void successCallback(Result_Api api) {

        }

        @Override
        public void failCallback() {

        }
    };

    @Override
    public void onAttached() {

    }

    @Override
    public void memberInfo() {

        mModel.memberInfo(postCallback);
    }
}
