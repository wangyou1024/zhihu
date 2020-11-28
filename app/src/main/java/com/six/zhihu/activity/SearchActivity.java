package com.six.zhihu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.flyco.tablayout.SlidingTabLayout;
import com.six.zhihu.R;
import com.six.zhihu.adapter.SearchAdapter;
import com.six.zhihu.fragment.search.DigitalFragment;
import com.six.zhihu.fragment.search.EncyclopediaFragment;
import com.six.zhihu.fragment.search.FilmFragment;
import com.six.zhihu.fragment.search.GameFragment;
import com.six.zhihu.fragment.search.ScienceFragment;
import com.six.zhihu.fragment.search.SearchFragment;
import com.six.zhihu.fragment.search.SportFragment;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热搜","数码", "影视","科学","体育","游戏","百科"
    };
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private Button btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        viewPager = findViewById(R.id.fixedViewPage);
        slidingTabLayout = findViewById(R.id.slidingTabLayout);
        btnQuit = findViewById(R.id.btn_quit);
        btnQuit.setOnClickListener(view -> finish());

        mFragments.add(SearchFragment.newInstance());
        mFragments.add(DigitalFragment.newInstance());
        mFragments.add(FilmFragment.newInstance());
        mFragments.add(ScienceFragment.newInstance());
        mFragments.add(SportFragment.newInstance());
        mFragments.add(GameFragment.newInstance());
        mFragments.add(EncyclopediaFragment.newInstance());
        viewPager.setAdapter(new SearchAdapter(getSupportFragmentManager(),mTitles,mFragments));
        slidingTabLayout.setViewPager(viewPager,mTitles);
        viewPager.setOffscreenPageLimit(mFragments.size());

    }
}