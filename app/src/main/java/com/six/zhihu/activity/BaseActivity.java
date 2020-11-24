package com.six.zhihu.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// 这是Activity的基类，建议实现这个类
public abstract class BaseActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
    }

    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();

}
