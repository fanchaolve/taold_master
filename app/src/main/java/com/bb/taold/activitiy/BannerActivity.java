package com.bb.taold.activitiy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.bb.taold.R;
import com.bb.taold.adapter.BannerViewPagerAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.widget.AlphaPageTransformer;

import butterknife.BindView;

/**
 * Created by zhucheng'an on 2017/8/21.
 * Package Name com.shoukb51.zhongm.app.activity
 * Class Name BannerActivity
 */

public class BannerActivity extends BaseActivity {


    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    public int getLayoutId() {
        return R.layout.layout_banner;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        fillViewPager();
    }

    public void fillViewPager() {
        mVpContent.setPageTransformer(false, new AlphaPageTransformer());
        BannerViewPagerAdapter mAdapter = new BannerViewPagerAdapter(BannerActivity.this);
        mVpContent.setAdapter(mAdapter);
        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpContent.setCurrentItem(0);
    }

}

