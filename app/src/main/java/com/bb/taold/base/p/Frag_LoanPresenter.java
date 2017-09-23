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
import com.bb.taold.utils.CacheUtils;
import com.bb.taold.utils.Constants;

import java.util.HashMap;
import java.util.Map;

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

    private PostCallback postCallback = null;

    @Override
    public void onAttached() {
        //callback 回调接受

    }

    @Override
    public void memberInfo() {

        mModel.memberInfo(postCallback);
    }

}
