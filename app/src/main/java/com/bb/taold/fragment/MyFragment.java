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
import butterknife.Unbinder;

import static com.bb.taold.R.id.advices;
import static com.bb.taold.R.id.lay_about_us;
import static com.bb.taold.R.id.lay_help;

/**
 * 类描述：
 * 创建时间：2017/9/12 0012
 *
 * @author chaochao
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.lay_my_header)
    LinearLayout mLayMyHeader;
    @BindView(R.id.lay_apply_records)
    LinearLayout mLayApplyRecords;
    @BindView(lay_help)
    LinearLayout mLayHelp;
    @BindView(advices)
    LinearLayout mAdvices;
    @BindView(lay_about_us)
    LinearLayout mLayAboutUs;
    @BindView(R.id.lay_messages)
    LinearLayout mLayMessages;
    @BindView(R.id.lay_exit)
    LinearLayout mLayExit;
    Unbinder unbinder;
    @BindView(R.id.tv_layouttitle_title)
    TextView mTvLayouttitleTitle;


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

    @OnClick({R.id.lay_my_header, R.id.lay_apply_records, R.id.lay_help, R.id.advices, R.id.lay_about_us,
            R.id.lay_messages, R.id.lay_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_my_header:
                AppManager.getInstance().showActivity(LoanRecordsActivity.class,null);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
