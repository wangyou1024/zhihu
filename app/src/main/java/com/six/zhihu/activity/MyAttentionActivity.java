package com.six.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.six.zhihu.R;
import com.six.zhihu.fragment.myAttention.ColumnFragment;
import com.six.zhihu.fragment.myAttention.RoundtableFragment;
import com.six.zhihu.fragment.myAttention.SpecialFragment;
import com.six.zhihu.fragment.myAttention.TopicFragment;
import com.six.zhihu.fragment.myAttention.UserFragment;

public class MyAttentionActivity extends BaseActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private LinearLayout linearLayout;
    @Override
    protected int initLayout() {
        return R.layout.activity_myattention;
    }

    @Override
    protected void initView() {
        viewPager2 = findViewById(R.id.myattention_viewpager);
        tabLayout = findViewById(R.id.myattention_tabLayout);
        ImageView imageView = findViewById(R.id.myattention_cancel);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment;
              switch(position) {
                   case 0: fragment =  new UserFragment();break;
                   case 1: fragment = new TopicFragment();break;
                  case 2: fragment = new ColumnFragment();break;
                  case 3: fragment = new SpecialFragment();break;
                  default: fragment = new RoundtableFragment();break;
              }
                Log.d("MyAttentionActivity", "切换Fragment");
               return fragment;
            }

            @Override
            public int getItemCount() {
                return 5;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("用户");
                        break;
                    case 1:
                        tab.setText("话题");
                        break;
                    case 2:
                        tab.setText("专栏");
                        break;
                    case 3:
                        tab.setText("专题");
                        break;
                    case 4:
                        tab.setText("圆桌");
                        break;
                }
                Log.d("MyAttentionActivity", "切换标题");
            }
        }).attach();
    }

}
