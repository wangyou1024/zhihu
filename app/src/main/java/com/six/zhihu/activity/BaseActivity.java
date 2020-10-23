package com.six.zhihu.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// 建议实现基类
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
