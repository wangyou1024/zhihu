package com.six.zhihu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;

public class SearchActivity extends BaseActivity {

    private ImageView ivReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(),2,"initLayout",2,R.layout.activity_search);
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        NormalLog.log(this.getClass(),2,"initView",0);
        ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener((view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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