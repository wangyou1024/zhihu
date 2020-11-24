package com.six.zhihu.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.activity.SearchActivity;
import com.six.zhihu.adapter.HomePagerAdapter;
import com.six.zhihu.fragment.home.CircleFragment;
import com.six.zhihu.fragment.home.ConcernFragment;
import com.six.zhihu.fragment.home.HotTopFragment;
import com.six.zhihu.fragment.home.RecommendFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create_center an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitle = {"关注", "推荐", "热榜", "圈子"};
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private EditText etSearch;

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
        // Inflate the profile for this fragment
        NormalLog.log(this.getClass(),2,"onCreateView",0);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = v.findViewById(R.id.vp_home);
        slidingTabLayout = v.findViewById(R.id.tl_home);
        etSearch = v.findViewById(R.id.et_search);
        NormalLog.log(this.getClass(),2,"onCreateView",1);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NormalLog.log(this.getClass(),2,"onViewCreated",0);
        super.onViewCreated(view, savedInstanceState);
        mFragment.add(ConcernFragment.newInstance());
        mFragment.add(RecommendFragment.newInstance());
        mFragment.add(HotTopFragment.newInstance());
        mFragment.add(CircleFragment.newInstance());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager(), mTitle, mFragment));
        slidingTabLayout.setViewPager(viewPager);
        etSearch.setOnFocusChangeListener((var1, var2)->{
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
            etSearch.clearFocus();
        });
        NormalLog.log(this.getClass(),2,"onViewCreated",1);

    }
}