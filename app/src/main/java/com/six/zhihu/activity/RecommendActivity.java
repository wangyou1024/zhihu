package com.six.zhihu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.six.zhihu.R;
import com.six.zhihu.entity.RecommendEntity;

public class RecommendActivity extends AppCompatActivity {

    private RecommendEntity recommendEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Intent intent = getIntent();
        recommendEntity = (RecommendEntity) intent.getSerializableExtra("recommendEntity");
        TextView title = findViewById(R.id.tv_recommend_title);
        title.setText(recommendEntity.getTitle());
    }
}