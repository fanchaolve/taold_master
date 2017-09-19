package com.bb.taold.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/19 0019
 *
 * @author chaochao
 */

public class GuidePagerAdapter extends android.support.v4.view.PagerAdapter{

    List<ImageView> viewLists;

    public GuidePagerAdapter(List<ImageView> lists)
    {
        viewLists = lists;
    }

    @Override
    public int getCount() {                                                                 //获得size  
        // TODO Auto-generated method stub  
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub  
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object)                       //销毁Item
    {
        view.removeView(viewLists.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position)                                //实例化Item
    {
        view.addView(viewLists.get(position), 0);

        return viewLists.get(position);
    }
}
