package com.bb.taold.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.my.LoanRecordsActivity;
import com.bb.taold.base.BaseFragment;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.tv_layouttitle_title)
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

    @OnClick({})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_my_header:
                AppManager.getInstance().showActivity(LoanRecordsActivity.class, null);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
