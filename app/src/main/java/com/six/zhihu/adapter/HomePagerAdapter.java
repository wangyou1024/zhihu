package com.six.zhihu.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.six.zhihu.NormalLog;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<Fragment> mFragments;
    public HomePagerAdapter(FragmentManager fm, String[] mTitles, ArrayList<Fragment> fragments) {
        super(fm);
        NormalLog.log(this.getClass(),2,"HomePagerAdapter",0);
        this.mTitles = mTitles;
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
