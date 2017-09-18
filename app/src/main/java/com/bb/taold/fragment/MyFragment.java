package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.EntireFactorPayActivity;
import com.bb.taold.activitiy.my.FeedbackActivity;
import com.bb.taold.activitiy.my.LoanRecordsActivity;
import com.bb.taold.activitiy.my.MyMessagesActivity;
import com.bb.taold.activitiy.webview.WebViewActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.tv_layout_title)
    TextView mTvLayouttitleTitle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mTvLayouttitleTitle.setText("我的");
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {

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
                bundle.putString(Constants.WEBVIEW_URL,"http://www.baidu.com");
                AppManager.getInstance().showActivity(WebViewActivity.class, bundle);
                break;
            case R.id.lay_feedback:
                AppManager.getInstance().showActivity(FeedbackActivity.class, null);
                break;
            case R.id.lay_about_us:
//                AppManager.getInstance().showActivity(AboutUsActivity.class, null);
                AppManager.getInstance().showActivity(EntireFactorPayActivity.class, null);
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
    }
}
