package com.six.zhihu.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.google.android.material.tabs.TabLayout;
import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.adapter.MessagePagerAdapter;
import com.six.zhihu.fragment.message.DynamicFragment;
import com.six.zhihu.fragment.message.MessageMenuFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    private ArrayList<Fragment> mFragment = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitle = {"动态", "消息"};
    static final int NUM_ITEMS = 2;
    ViewPager vp_message;
    TabLayout tl_message;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
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
        NormalLog.log(this.getClass(),2,"onCreateView",0);
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        vp_message = v.findViewById(R.id.vp_message);
        tl_message = v.findViewById(R.id.tl_message);
        NormalLog.log(this.getClass(),2,"onCreateView",1);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NormalLog.log(this.getClass(),2,"onViewCreated",0);
        mFragment.add(DynamicFragment.newInstance());
        mFragment.add(MessageMenuFragment.newInstance());
        vp_message.setOffscreenPageLimit(2);
        vp_message.setAdapter(new MessagePagerAdapter(getFragmentManager(), mTitle, mFragment));
        tl_message.setupWithViewPager(vp_message);


        NormalLog.log(this.getClass(),2,"onViewCreated",1);

    }
}