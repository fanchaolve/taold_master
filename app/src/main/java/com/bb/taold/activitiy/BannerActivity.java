package com.bb.taold.activitiy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.KeyEvent;

import com.bb.taold.R;
import com.bb.taold.adapter.BannerViewPagerAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.PreferenceUtil;
import com.bb.taold.widget.AlphaPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhucheng'an on 2017/8/21.
 * Package Name com.shoukb51.zhongm.app.activity
 * Class Name BannerActivity
 */

public class BannerActivity extends BaseActivity {


    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.iv_banner1)
    ImageView mIvBanner1;
    @BindView(R.id.iv_banner2)
    ImageView mIvBanner2;
    @BindView(R.id.iv_banner3)
    ImageView mIvBanner3;
    @BindView(R.id.iv_banner4)
    ImageView mIvBanner4;
    @BindView(R.id.ll_line)
    LinearLayout mLlLine;
    @BindView(R.id.tv_btn)
    TextView mTvBtn;


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
                switch (position){
                    case 0:
                        mLlLine.setVisibility(View.VISIBLE);
                        mTvBtn.setVisibility(View.GONE);
                        mIvBanner1.setImageResource(R.drawable.bg_dot_yellow);
                        mIvBanner2.setImageResource(R.drawable.bg_dot);
                        mIvBanner3.setImageResource(R.drawable.bg_dot);
                        mIvBanner4.setImageResource(R.drawable.bg_dot);
                        break;
                    case 1:
                        mLlLine.setVisibility(View.VISIBLE);
                        mTvBtn.setVisibility(View.GONE);
                        mIvBanner1.setImageResource(R.drawable.bg_dot);
                        mIvBanner2.setImageResource(R.drawable.bg_dot_yellow);
                        mIvBanner3.setImageResource(R.drawable.bg_dot);
                        mIvBanner4.setImageResource(R.drawable.bg_dot);
                        break;
                    case 2:
                        mLlLine.setVisibility(View.VISIBLE);
                        mTvBtn.setVisibility(View.GONE);
                        mIvBanner1.setImageResource(R.drawable.bg_dot);
                        mIvBanner2.setImageResource(R.drawable.bg_dot);
                        mIvBanner3.setImageResource(R.drawable.bg_dot_yellow);
                        mIvBanner4.setImageResource(R.drawable.bg_dot);
                        break;
                    case 3:
                        mLlLine.setVisibility(View.GONE);
                        mTvBtn.setVisibility(View.VISIBLE);
                        mTvBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PreferenceUtil.saveBSharedPreference(BannerActivity.this,PreferenceUtil.isNewUser,true);
                                AppManager.getInstance().showActivity(HomeActivity.class, null);
                            }
                        });
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpContent.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                showTip(getString(R.string.snack_exit_once_more));
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                AppManager.getInstance().AppExit(this);
            }


        }
        return super.onKeyDown(keyCode, event);

    }


}

