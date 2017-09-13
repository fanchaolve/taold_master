package com.bb.taold.listener;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.authreal.api.OnResultListener;
import com.bb.taold.api.ApiService;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.AuthMessage;
import com.bb.taold.bean.AuthParam;
import com.bb.taold.bean.AuthResult;
import com.bb.taold.fragment.Auth_StateFragment;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.GsonUtils;

import retrofit2.Call;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.listener
 * <p>
 * 说明：人脸识别回调返回
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/12
 * <p>
 * ==============================================
 */

public class AuthOnresultListener implements OnResultListener {

    private PostCallback postCallback;

    private AuthParam param;

    private ApiService service;

    private BaseActivity mContext;

    public AuthOnresultListener(BaseActivity mContext, PostCallback postCallback, AuthParam param, ApiService service) {

        this.postCallback = postCallback;

        this.param = param;

        this.service = service;
        this.mContext = mContext;
    }

    @Override
    public void onResult(String s) {
        Log.i("fancl", "人脸识别返回结果:" + s);
        if (s != null && s.length() > 0) {
            AuthResult result = GsonUtils.fromJson(s, AuthResult.class);

            if("900001".equalsIgnoreCase(result.getRet_code())){
                mContext.showMsg(result.getRet_msg());
                return;
            }

            if ("F".equalsIgnoreCase(result.getResult_auth())) {

                Bundle bundle = new Bundle();
                bundle.putInt("state", -1);
                bundle.putSerializable("AuthParam",param);
                AppManager.getInstance().showAuth_State(mContext, new Auth_StateFragment(), bundle);
                return;
            }

            if (param == null || param.getOutOrderId() == null || param.getOutOrderId().length() == 0)
                return;
            Call<Result_Api<AuthMessage>> call = service.ocr_authStatus(param.getOutOrderId());
            call.enqueue(postCallback);
        }
    }
}
