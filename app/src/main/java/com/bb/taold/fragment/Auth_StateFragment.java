package com.bb.taold.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.AuthInfoActivity;
import com.bb.taold.api.GenApiHashUrl;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.base.v.BaseView;
import com.bb.taold.bean.AuthInfo;
import com.bb.taold.bean.AuthParam;
import com.bb.taold.listener.AuthOnresultListener;
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
 * 说明：人脸识别的审核状态
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/12
 * <p>
 * ==============================================
 */

public class Auth_StateFragment extends BaseFragment implements View.OnClickListener{

    private int state;//人脸识别认证的状态 -1 就是客户端识别失败, 0 就是成功，等待状态，1 就是 服务端审核失败,2服务端审核成功


    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    @BindView(R.id.iv_state)
    ImageView iv_state;

    @BindView(R.id.tv_detail)
    TextView tv_detail;

    private AuthParam param;


    private PostCallback postCallback =new PostCallback(this) {
        @Override
        public void successCallback(Result_Api api) {
            if(api.getT() instanceof AuthInfo){
                AuthInfo info = (AuthInfo) api.getT();
                if (info == null)
                    return;
                if(info.isFaceAuth()){//成功
                    Bundle bundle =new Bundle();
                    bundle.putInt("state", 2);
                    AppManager.getInstance().showAuth_State((BaseActivity) getActivity(),new Auth_StateFragment(),bundle);
                }else {
                    Bundle bundle =new Bundle();
                    bundle.putInt("state", 1);
                    AppManager.getInstance().showAuth_State((BaseActivity) getActivity(),new Auth_StateFragment(),bundle);
                }
            }
        }

        @Override
        public void failCallback() {

        }
    };



    @Override
    public int getLayoutId() {

        return R.layout.fragment_auth_state;
    }

    @Override
    public void initView() {
        tv_confirm.setOnClickListener(this);

    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        if(getActivity() instanceof AuthInfoActivity){
            ((AuthInfoActivity) getActivity()).goStep(1);
        }

        if(state == -1){//失败
            iv_state.setImageResource(R.drawable.face_faild);
            tv_detail.setText(getString(R.string.face_sorry));
            tv_confirm.setText("重新认证");
            tv_confirm.setVisibility(View.VISIBLE);
        }else if(state == 0){
            iv_state.setImageResource(R.drawable.face_loading);
            tv_detail.setText(getString(R.string.face_loading));
            tv_confirm.setVisibility(View.GONE);
            getFace_State(1000);

        }else  if(state ==1){
            iv_state.setImageResource(R.drawable.face_faild);
            tv_detail.setText(getString(R.string.face_sorry1));
            tv_confirm.setText("重新获取结果");
            tv_confirm.setVisibility(View.VISIBLE);
        }else {//成功
            iv_state.setImageResource(R.drawable.face_success);
            tv_detail.setText(getString(R.string.face_sorry1));
            tv_confirm.setText(getString(R.string.confim));
            tv_confirm.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            if (bundle.containsKey("state")) {
                state = bundle.getInt("state");
            }

            if(bundle.containsKey("AuthParam")){
                param= (AuthParam) bundle.getSerializable("AuthParam");
            }
        }
    }

    private void getFace_State(long times){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<Result_Api<AuthInfo>> call =service.member_identityAuthInfo();
                call.enqueue(postCallback);
            }
        },times);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                if(state == -1){
                    if(param ==null)
                        return;
                    AuthUtil.faceAuth(mContext, param.getOutOrderId(),
                            param.getAuthKey(), GenApiHashUrl.apiUrl + "/gateway?method=" + param.getNotifyMethod() +
                                    "&" + new ParamUtils().getAuthParams().toString(), new AuthOnresultListener(
                                    (AuthInfoActivity) getActivity(),postCallback,param,service
                            ));
                }else if(state == 1){//服务失败
                    getFace_State(10);
                }else if(state ==2){
                    //跳转到另外页面
                    AppManager.getInstance().showAuthMoblie((BaseActivity) getActivity(),new Mobile_Phone_OperatorsFragment());
                }
                break;
        }
    }
}
