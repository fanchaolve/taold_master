package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
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
import com.bb.taold.utils.PayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private UserInfo info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

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
        PostCallback postCallback = new PostCallback<BaseActivity>() {
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
        Call<Result_Api<UserInfo>> call = service.user_info();
        Callexts.need_sessionPost(call, postCallback);
    }


    private void setViewData(UserInfo info) {
        String mobile = info.getMobile();
        mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        mTvUserPhone.setText(mobile);
        if (info.getAuthentication() == Constants.AUTO_STATE_OK) {
            mTvAuthStateName.setText("已认证");
            mIvAuthFlag.setVisibility(View.VISIBLE);
        } else {
            mTvAuthStateName.setText("未认证");
            mIvAuthFlag.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.lay_apply_records, R.id.lay_my_messages, R.id.lay_help, R.id.lay_feedback, R.id.lay_about_us, R.id.lay_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_apply_records:
                AppManager.getInstance().showActivity(LoanRecordsActivity.class, null);
                break;
            case R.id.lay_my_messages:
                AppManager.getInstance().showActivity(MyMessagesActivity.class, null);
                break;
            case R.id.lay_help:
                Bundle bundle = new Bundle();
                bundle.putString(Constants.WEBVIEW_URL, "http://www.baidu.com");
                AppManager.getInstance().showActivity(WebViewActivity.class, bundle);
                break;
            case R.id.lay_feedback:
                AppManager.getInstance().showActivity(FeedbackActivity.class, null);
                break;
            case R.id.lay_about_us:
//                AppManager.getInstance().showActivity(AboutUsActivity.class, null);
//                AppManager.getInstance().showActivity(EntireFactorPayActivity.class, null);
                PayUtil payUtil = new PayUtil(mContext, "27324533988150081352", "100100102", "073391932335593", "100", "20170918172445");
                payUtil.startPay();
                break;

            case R.id.lay_logout://退出登录
                getActivity().finish();
                AppManager.getInstance().logout();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
