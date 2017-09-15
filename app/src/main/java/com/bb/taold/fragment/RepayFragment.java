package com.bb.taold.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.HomeActivity;
import com.bb.taold.adapter.PagerAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.fragment
 * Class Name RepayFragment
 */

public class RepayFragment extends BaseFragment {

    //ViewPager适配器
    PagerAdapter mAdapter = null;
    @BindView(R.id.tv_layout_title)
    TextView mTvLayoutTitle;
    @BindView(R.id.tv_unpay)
    TextView mTvUnpay;
    @BindView(R.id.view_line_unpay)
    View mViewLineUnpay;
    @BindView(R.id.tv_allPay)
    TextView mTvAllPay;
    @BindView(R.id.view_line_allpay)
    View mViewLineAllpay;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;



    @Override
    public int getLayoutId() {
        return R.layout.fragment_repay;
    }

    @Override
    public void initView() {

        mTvLayoutTitle.setText("账单");
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
        // 必须继承FragmentActivity才能用getSupportFragmentManager()；
        mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        mVpContent.setAdapter(mAdapter);
        // 监听页面变化
        mVpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                setCurrentPage(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        setCurrentPage(0);// 默认选中样式
    }

    /**
     * 页面与head标签一致（可以设置head的按钮样式）
     *
     * @param arg0
     */
    private void setCurrentPage(int arg0) {
        switch (arg0) {
            //未还账单
            case 0:
                //未还账单字体颜色和底部线条
                mTvUnpay.setTextColor(getResources().getColor(R.color.color_price));
                mViewLineUnpay.setVisibility(View.VISIBLE);
                //全部账单字体颜色和底部线条
                mTvAllPay.setTextColor(getResources().getColor(R.color.color_111111));
                mViewLineAllpay.setVisibility(View.GONE);
                break;
            //全部账单
            case 1:
                mTvUnpay.setTextColor(getResources().getColor(R.color.color_111111));
                mViewLineUnpay.setVisibility(View.GONE);
                //全部账单字体颜色和底部线条
                mTvAllPay.setTextColor(getResources().getColor(R.color.color_price));
                mViewLineAllpay.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.rl_unpay, R.id.rl_allpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_unpay:
                setCurrentPage(0);
                break;
            case R.id.rl_allpay:
                setCurrentPage(1);
                break;
        }
    }
}
