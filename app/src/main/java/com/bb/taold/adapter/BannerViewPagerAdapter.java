package com.bb.taold.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.HomeActivity;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.utils.AppManager;


/**
 * Created by zhucheng'an on 2017/8/22.
 * Package Name com.bnh51.zhongma.app.adapter
 * Class Name BannerViewPagerAdapter
 */

public class BannerViewPagerAdapter extends PagerAdapter {

    private Activity mActivity;

    public BannerViewPagerAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // TODO Auto-generated method stub

        View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_banner_item, null);
        ImageView mBannerImg = (ImageView) view.findViewById(R.id.banner_img);
        LinearLayout banner = (LinearLayout) view.findViewById(R.id.ll_line);
        ImageView line1 = (ImageView) view.findViewById(R.id.iv_banner1);
        ImageView line2 = (ImageView) view.findViewById(R.id.iv_banner2);
        ImageView line3 = (ImageView) view.findViewById(R.id.iv_banner3);
        ImageView line4 = (ImageView) view.findViewById(R.id.iv_banner4);
        TextView bannerBtn = (TextView) view.findViewById(R.id.tv_btn);
        switch (position % 4) {
            case 0:
                mBannerImg.setBackground(mActivity.getResources().getDrawable(R.drawable.banner1));
                bannerBtn.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                line1.setBackgroundResource(R.drawable.bg_dot_yellow);
                line2.setBackgroundResource(R.drawable.bg_dot);
                line3.setBackgroundResource(R.drawable.bg_dot);
                line4.setBackgroundResource(R.drawable.bg_dot);
                break;
            case 1:
                mBannerImg.setBackground(mActivity.getResources().getDrawable(R.drawable.banner2));
                bannerBtn.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                line1.setBackgroundResource(R.drawable.bg_dot);
                line2.setBackgroundResource(R.drawable.bg_dot_yellow);
                line3.setBackgroundResource(R.drawable.bg_dot);
                line4.setBackgroundResource(R.drawable.bg_dot);
                break;
            case 2:
                mBannerImg.setBackground(mActivity.getResources().getDrawable(R.drawable.banner3));
                bannerBtn.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                line1.setBackgroundResource(R.drawable.bg_dot);
                line2.setBackgroundResource(R.drawable.bg_dot);
                line3.setBackgroundResource(R.drawable.bg_dot_yellow);
                line4.setBackgroundResource(R.drawable.bg_dot);
                break;
            case 3:
                mBannerImg.setBackground(mActivity.getResources().getDrawable(R.drawable.banner4));
                bannerBtn.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                bannerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("".equalsIgnoreCase(MyApplication.getInstance().getSession())) {//未登陆状态
                            AppManager.getInstance().showActivity(LoginActivity.class, null);
                        } else {//登陆状态
                            AppManager.getInstance().showActivity(HomeActivity.class, null);
                        }
                        mActivity.finish();
                    }
                });
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
//        ((ViewPager)container).removeView(mListViews.get(position%4));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

}