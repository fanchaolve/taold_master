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
        postCallback = new PostCallback<Frag_LoanContract.View>(mView) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof AuthInfo) {
                    AuthInfo info = (AuthInfo) api.getT();
                    if (info == null)
                        return;
                    if (info.getFlag() > 0 && info.getFlag() < 6) {//步骤视图里面
                        Bundle bundle = new Bundle();
                        bundle.putInt("flag", info.getFlag());
                        bundle.putSerializable(Constants.AUTHOINFO, info);
                        AppManager.getInstance().showActivity(AuthInfoActivity.class, bundle);
                    } else if (info.getFlag() == 6) {//绑定主卡
                        AppManager.getInstance().showActivity(AddBankCardActivity.class, null);
                    } else {
                        //条件满足，跳转到确认借款页面
                        Bundle mBundle = new Bundle();
                        //银行卡认证后使用
                        Map<String,String> map = new HashMap<>();
                        //总金额
                        mBundle.putString("loanAmount",mView.getTotalAmount());
                        map.put("loanAmount",mView.getTotalAmount());

                        //当前使用id
                        if(mView.is7Id()){
                            mBundle.putString("stage7Id",mView.getCurrentId());
                            map.put("stage7Id",mView.getCurrentId());
                        }else{
                            mBundle.putString("stage14Id",mView.getCurrentId());
                            map.put("stage14Id",mView.getCurrentId());
                        }
                        CacheUtils.saveDataToDiskLruCache(Constants.TO_CONFIRM_ACTIVIY,map);
                        AppManager.getInstance().showActivity(LoanConfirmActivity.class,mBundle);
                    }
                }
            }

            @Override
            public void failCallback() {

            }
        };
    }

    @Override
    public void memberInfo() {

        mModel.memberInfo(postCallback);
    }

}
