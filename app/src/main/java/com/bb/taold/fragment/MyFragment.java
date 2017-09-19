package com.bb.taold.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.activitiy.my.FeedbackActivity;
import com.bb.taold.activitiy.my.LoanRecordsActivity;
import com.bb.taold.activitiy.my.MyMessagesActivity;
import com.bb.taold.activitiy.webview.WebViewActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.EBJPayUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;


/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.tv_layout_title)
    TextView mTvLayoutTitle;
    @BindView(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @BindView(R.id.lay_my_header)
    LinearLayout mLayMyHeader;
    @BindView(R.id.lay_apply_records)
    LinearLayout mLayApplyRecords;
    @BindView(R.id.lay_my_messages)
    LinearLayout mLayMyMessages;
    @BindView(R.id.lay_help)
    LinearLayout mLayHelp;
    @BindView(R.id.lay_feedback)
    LinearLayout mLayFeedback;
    @BindView(R.id.lay_about_us)
    LinearLayout mLayAboutUs;
    @BindView(R.id.lay_logout)
    LinearLayout mLayLogout;
    Unbinder unbinder;
    @BindView(R.id.tv_auth_state_name)
    TextView mTvAuthStateName;
    @BindView(R.id.iv_auth_flag)
    ImageView mIvAuthFlag;

    @BindView(R.id.iv_head)
    ImageView iv_head;

    @BindView(R.id.tv_confirm)
    TextView tv_confirm;


    private UserInfo info;

    PostCallback postCallback;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mTvLayoutTitle.setText("我的");
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        setLogoutViewData();

        postCallback = new PostCallback<MyFragment>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof UserInfo) {
                    info = (UserInfo) api.getT();
                    setViewData(info);
                }
            }


            @Override
            public void failCallback() {

            }
        };

    }

//
    private void setViewData(UserInfo info) {
        String mobile = info.getMobile();
        mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        mTvUserPhone.setText(mobile);
        mTvAuthStateName.setVisibility(View.VISIBLE);
        if (info.getAuthentication() == Constants.AUTO_STATE_OK) {
            mTvAuthStateName.setText("已认证");
            mIvAuthFlag.setVisibility(View.VISIBLE);
        } else {
            mTvAuthStateName.setText("未认证");
            mIvAuthFlag.setVisibility(View.GONE);
        }
        iv_head.setImageResource(R.drawable.my_header);
        tv_confirm.setText("退出登录");
    }

    //登出的页面刷新
    private void setLogoutViewData(){
        iv_head.setImageResource(R.drawable.head_portrait_not_login);
        mTvUserPhone.setText("登录");
        mTvAuthStateName.setVisibility(View.GONE);
        mIvAuthFlag.setVisibility(View.GONE);
        tv_confirm.setText("登录");
    }

    @OnClick({R.id.lay_apply_records, R.id.lay_my_messages, R.id.lay_help, R.id.lay_feedback, R.id.lay_about_us, R.id.lay_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_apply_records:
                if (AppManager.getInstance().isLogin())
                    AppManager.getInstance().showActivity(LoanRecordsActivity.class, null);
                else
                    AppManager.getInstance().showActivity(LoginActivity.class, null);

                break;
            case R.id.lay_my_messages:
                if (AppManager.getInstance().isLogin())
                    AppManager.getInstance().showActivity(MyMessagesActivity.class, null);
                else
                    AppManager.getInstance().showActivity(LoginActivity.class, null);
                break;
            case R.id.lay_help:
                Bundle bundle = new Bundle();
                bundle.putString(Constants.WEBVIEW_URL, "http://www.baidu.com");
                AppManager.getInstance().showActivity(WebViewActivity.class, bundle);
                break;
            case R.id.lay_feedback:
                if (AppManager.getInstance().isLogin())
                    AppManager.getInstance().showActivity(FeedbackActivity.class, null);
                else
                    AppManager.getInstance().showActivity(LoginActivity.class, null);
                break;
            case R.id.lay_about_us:
//                AppManager.getInstance().showActivity(AboutUsActivity.class, null);
//                AppManager.getInstance().showActivity(EntireFactorPayActivity.class, null);
                EBJPayUtil payUtil = new EBJPayUtil(mContext, "29816070985499016640", "100100102", "083012498311295", "0.01", "20170918181531");
                payUtil.startPay();
                break;

            case R.id.lay_logout://退出登录或许是登陆
//                getActivity().finish();

                AppManager.getInstance().logout();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    @Override
    public void onStart() {
        super.onStart();
        if(AppManager.getInstance().isLogin()){
            if(info==null && isVisible()) {//如果登录了 就没必要重复调用接口了
                Call<Result_Api<UserInfo>> call = service.user_info();
                Callexts.need_sessionPost(call, postCallback);
            }
        }else {
            info =null;
            setLogoutViewData();
        }
    }
}
