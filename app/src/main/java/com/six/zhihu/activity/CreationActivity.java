package com.six.zhihu.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.six.zhihu.R;

public class CreationActivity extends BaseActivity {
    private ImageView imageView;
    @Override
    protected int initLayout() {
        return R.layout.activity_creation;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.creation_cancel);
        imageView.setOnClickListener(view -> {
            finish();
            Log.d("CreationActivity", "结束我的创作页面");
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
