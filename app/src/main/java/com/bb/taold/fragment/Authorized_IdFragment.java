package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.authreal.api.OnResultListener;
import com.bb.taold.R;
import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.api.GenApiHashUrl;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.bean.AuthParam;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.AuthUtil;
import com.bb.taold.utils.ParamUtils;

import butterknife.BindView;
import retrofit2.Call;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.fragment
 * <p>
 * 说明：身份认证
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/9
 * <p>
 * ==============================================
 */

public class Authorized_IdFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    private PostCallback postCallback;//接口返回接受

    private OnResultListener listener=new OnResultListener() {
        @Override
        public void onResult(String s) {

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_authorized_idently;
    }

    @Override
    public void initView() {
        tv_confirm.setOnClickListener(this);
        postCallback=new PostCallback(this) {
            @Override
            public void successCallback(Result_Api api) {
                AuthParam param = (AuthParam) api.getT();
                if(param==null)
                    return;
                AuthUtil.faceAuth(mContext,param.getOutOrderId(),
                        param.getAuthKey(), GenApiHashUrl.apiUrl+"/gateway?method="+param.getNotifyMethod()+
                "&"+new ParamUtils().getAuthParams().toString(),listener);
            }

            @Override
            public void failCallback() {

            }
        };
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
//                AppManager.getInstance().showAuthMoblie((AuthInfoActivity) getActivity(), new Mobile_Phone_OperatorsFragment());
//                ((AuthInfoActivity) getActivity()).goStep(2);

                Call<Result_Api<AuthParam>> call = service.ocr_init();
                call.enqueue(postCallback);
                break;
        }
    }
}
