package com.six.zhihu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.entity.RecommendEntity;

public class RecommendActivity extends BaseActivity {

    private RecommendEntity recommendEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NormalLog.log(this.getClass(),2,"onCreate",0);
        initData();
        super.onCreate(savedInstanceState);
        NormalLog.log(this.getClass(),2, "onCreate", 1);
    }

    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(),2, "initLayout",2,R.layout.activity_recommend);
        return R.layout.activity_recommend;
    }

    @Override
    protected void initView() {
        NormalLog.log(this.getClass(),2,"initView",0);
        TextView title = findViewById(R.id.tv_recommend_title);
        title.setText(recommendEntity.getTitle());
        NormalLog.log(this.getClass(),2,"initView",1);
    }

    @Override
    protected void initData() {
        NormalLog.log(this.getClass(),2, "initView",0);
        Intent intent = getIntent();
        recommendEntity = (RecommendEntity) intent.getSerializableExtra("recommendEntity");
        NormalLog.log(this.getClass(),2,"initView", 1);
    }
}