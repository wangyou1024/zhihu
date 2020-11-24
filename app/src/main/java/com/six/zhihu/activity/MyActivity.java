package com.six.zhihu.activity;

import android.content.Intent;
import android.widget.TextView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;

public class MyActivity extends BaseActivity {
    TextView profile;
    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(),2,"initLayout",2, R.layout.fragment_my);
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        NormalLog.log(this.getClass(),2,"initView",0);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener((view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            this.finish();
        }));
        NormalLog.log(this.getClass(),2,"initView",1);

    }

    @Override
    protected void initData() {
        NormalLog.log(this.getClass(),2,"initData",1);

    }
}
