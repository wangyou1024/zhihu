package com.six.zhihu.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.six.zhihu.R;
import com.six.zhihu.adapter.HomePagerAdapter;
import com.six.zhihu.fragment.home.CircleFragment;
import com.six.zhihu.fragment.home.ConcernFragment;
import com.six.zhihu.fragment.home.HotTopFragment;
import com.six.zhihu.fragment.home.RecommendFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitle = {"关注", "推荐", "热榜", "圈子"};
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = v.findViewById(R.id.vp_home);
        slidingTabLayout = v.findViewById(R.id.tl_home);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment.add(ConcernFragment.newInstance());
        mFragment.add(RecommendFragment.newInstance());
        mFragment.add(HotTopFragment.newInstance());
        mFragment.add(CircleFragment.newInstance());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager(), mTitle, mFragment));
        slidingTabLayout.setViewPager(viewPager);
    }
}