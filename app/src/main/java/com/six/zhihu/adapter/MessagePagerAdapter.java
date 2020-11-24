package com.six.zhihu.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.six.zhihu.NormalLog;

import java.util.ArrayList;

public class MessagePagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private ArrayList<Fragment> mFragments;

    public MessagePagerAdapter(@NonNull FragmentManager fm, String[] mTitles, ArrayList<Fragment> fragments) {
        super(fm);
        NormalLog.log(this.getClass(),2,"MessagePagerAdapter",0);
        this.mTitles = mTitles;
        this.mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
