package com.bb.taold.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;


import com.bb.taold.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancl on 2016/12/21.
 */

public abstract class BasePageAdapter<T extends BaseFragment, L> extends FragmentStatePagerAdapter {


    public static final String TAG = BasePageAdapter.class.getSimpleName();

    public List<L> listtitle;

    public List<BaseFragment> list = new ArrayList<>();


    public BasePageAdapter(FragmentManager fm, List<L> listtitle) {
        super(fm);
        this.listtitle = listtitle;
    }


    public abstract  void addFragment(T fragment);


    public void removeFragment(T fragment) {
        list.remove(fragment);
    }

    public void clear() {
        for (BaseFragment fragment : list) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        list.clear();
    }


    @Override
    public Fragment getItem(int position) {
//        Bundle bundle = new Bundle();
//        Fragment itemFragment = list.get(position);
//        bundle.putInt(Constants.TYPE, position);
//        itemFragment.setArguments(bundle);
        return getFragement(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem position = " + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        if (listtitle == null)
            return 0;
        else
            return listtitle.size();
    }


    public abstract CharSequence getPageTitle(int position);

    public abstract BaseFragment getFragement(int position);
}
