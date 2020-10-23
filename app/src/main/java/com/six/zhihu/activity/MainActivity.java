package com.six.zhihu.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.six.zhihu.R;
import com.six.zhihu.adapter.MainPagerAdapter;
import com.six.zhihu.entity.TabEntity;
import com.six.zhihu.fragment.FoundFragment;
import com.six.zhihu.fragment.HomeFragment;
import com.six.zhihu.fragment.MemberFragment;
import com.six.zhihu.fragment.MessageFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitle = {"首页", "会员", "发现", "消息", "我的"};
    private int[] mIconUnSelectIds = {
        R.mipmap.home_tab_unselect, R.mipmap.member_tab_unselect,R.mipmap.found_tab_unselect,
            R.mipmap.message_tab_unselect, R.mipmap.my_tab_unselect
    };
    private int[] mIconSelectIds = {
            R.mipmap.home_tab_select, R.mipmap.mermber_tab_select, R.mipmap.found_tab_select,
            R.mipmap.message_tab_select, R.mipmap.my_tab_select
    };
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout(){
        return R.layout.activity_main;
    }

    @Override
    protected void initView(){
        viewPager = findViewById(R.id.vp_main);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData(){
        mFragment.add(HomeFragment.newInstance());
        mFragment.add(MemberFragment.newInstance());
        mFragment.add(FoundFragment.newInstance());
        mFragment.add(MessageFragment.newInstance());
        mFragment.add(MessageFragment.newInstance());
        for (int i = 0; i < mTitle.length; i++){
            mTabEntities.add(new TabEntity(mTitle[i], mIconSelectIds[i],mIconUnSelectIds[i]));
        }
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mTitle, mFragment));
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}