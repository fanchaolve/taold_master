package com.bb.taold.activitiy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bb.taold.R;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.adapter.GuidePagerAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.PreferenceUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 类描述：
 * 创建时间：2017/9/19 0019
 *
 * @author chaochao
 */

public class GuideActivity extends BaseActivity {
    private int[] imageIds = new int[]{R.drawable.guide_one,R.drawable.guide_two,R.drawable.guide_three,R.drawable.guide_four};
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
        for(int id:imageIds){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(id);
            imageViews.add(imageView);
            if(id==imageIds[imageIds.length-1]){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppManager.getInstance().showActivity(LoginActivity.class,null);
                        PreferenceUtil.saveSharedPreference(mContext,PreferenceUtil.isNewUser, Constants.NEW_USER_FLAG);
                        finish();
                    }
                });
            }
        }
        GuidePagerAdapter pagerAdapter = new GuidePagerAdapter(imageViews);
        mViewpagerGuide.setAdapter(pagerAdapter);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
}
