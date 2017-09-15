package com.bb.taold.base.p;

import android.os.Bundle;

import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.activitiy.addBankCard.AddBankCardActivity;
import com.bb.taold.activitiy.loan.LoanConfirmActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.v.Frag_LoanContract;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;

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
    private PostCallback postCallback = new PostCallback<Frag_LoanContract.View>(mView) {
        @Override
        public void successCallback(Result_Api api) {
            if (api.getT() instanceof AuthInfo) {
                AuthInfo info = (AuthInfo) api.getT();
                if (info == null)
                    return;
                if (info.getFlag() > 0 || info.getFlag() < 6) {//步骤视图里面
                    Bundle bundle = new Bundle();
                    bundle.putInt("flag", info.getFlag());
                    bundle.putSerializable(Constants.AUTHOINFO, info);
                    AppManager.getInstance().showActivity(AuthInfoActivity.class, bundle);
                } else if (info.getFlag() == 6) {//绑定主卡
                    AppManager.getInstance().showActivity(AddBankCardActivity.class, null);

                } else {//条件满足

                }
            }
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
