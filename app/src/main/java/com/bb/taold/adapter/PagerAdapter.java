package com.bb.taold.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bb.taold.fragment.AllpayFragment;
import com.bb.taold.fragment.UnpayFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> list_fragments;

	public PagerAdapter(FragmentManager fm) {
		super(fm);
		list_fragments = new ArrayList<Fragment>();
		list_fragments.add(new UnpayFragment());
		list_fragments.add(new AllpayFragment());
	}

	@Override
	public Fragment getItem(int arg0) {
		return list_fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return list_fragments != null ? list_fragments.size() : 0;
	}

}
