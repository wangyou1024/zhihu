package com.six.zhihu.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.six.zhihu.R;

public class UserinfoActivity  extends BaseActivity{
    ImageView imageView;
    @Override
    protected int initLayout() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initView() {

        imageView = findViewById(R.id.userinfo_cancel);

        Log.d("UserinfoActivity", "结束用户详细页面");
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
    }
}
