package com.bb.taold.activitiy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.adapter.GuidePagerAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.PreferenceUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类描述：
 * 创建时间：2017/9/19 0019
 *
 * @author chaochao
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.lay_dots_container) RadioGroup mLayDots;
    @BindView(R.id.btn_to_main) TextView mTvToNext;
    private int[] imageIds = new int[]{R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_three, R.drawable.guide_four};
    @BindView(R.id.viewpager_guide)
    ViewPager mViewpagerGuide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        RadioButton radioButton = (RadioButton) mLayDots.getChildAt(0);
        radioButton.setChecked(true);
        for (int id : imageIds) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(id);
            imageViews.add(imageView);
        }
        GuidePagerAdapter pagerAdapter = new GuidePagerAdapter(imageViews);
        mViewpagerGuide.setAdapter(pagerAdapter);

    }

    @Override
    public void initListener() {
        mViewpagerGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override public void onPageSelected(int position) {
                if(position<3){
                    mLayDots.check(mLayDots.getChildAt(position).getId());
                    mLayDots.setVisibility(View.VISIBLE);
                    mTvToNext.setVisibility(View.INVISIBLE);
                }else{
                    mLayDots.setVisibility(View.INVISIBLE);
                    mTvToNext.setVisibility(View.VISIBLE);
                }
            }

            @Override public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initdata() {

    }

    @OnClick(R.id.btn_to_main)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_to_main:
                AppManager.getInstance().showActivity(LoginActivity.class, null);
                PreferenceUtil.saveSharedPreference(mContext, PreferenceUtil.isNewUser, Constants.NEW_USER_FLAG);
                finish();
                break;
        }
    }
}
