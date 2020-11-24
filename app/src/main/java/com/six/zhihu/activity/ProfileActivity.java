package com.six.zhihu.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.adapter.RecommendAdapter;
import com.six.zhihu.dao.DBOpenHelper;
import com.six.zhihu.entity.RecommendEntity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity {
    private Button edit_profile;
    private RecyclerView recyclerView;
    private RecommendAdapter recommendAdapter;
    private ImageView back2My;


    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(), 2, "initLayout", 2, R.layout.profile);
        return R.layout.profile;
    }

    @Override
    protected void initView() {
        NormalLog.log(this.getClass(), 2, "initView", 0);
        back2My = findViewById(R.id.back_2_my);
        back2My.setOnClickListener((view -> {
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
            this.finish();
        }));
        edit_profile = findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener((view -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
            this.finish();
        }));
        recyclerView = findViewById(R.id.profile_recyclerView);

        NormalLog.log(this.getClass(), 2, "initView", 1);

    }

    @Override
    protected void initData() {
        NormalLog.log(this.getClass(), 2, "initData", 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recommendAdapter = new RecommendAdapter(this);
        recommendAdapter.setOnItemClickListener(obj -> {
            RecommendEntity recommendEntity = (RecommendEntity) obj;
            Toast.makeText(this, recommendEntity.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(this, RecommendActivity.class);
            intent.putExtra("recommendEntity", recommendEntity);
            startActivity(intent);
        });
        getInfo();
        recyclerView.setAdapter(recommendAdapter);
    }

    public void getInfo() {
        NormalLog.log(this.getClass(), 2, "getInfo", 0);
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from recommend", new String[]{});
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String concern = cursor.getString(cursor.getColumnIndex("concern"));
            Integer header = cursor.getInt(cursor.getColumnIndex("header"));
            Integer image = cursor.getInt(cursor.getColumnIndex("image"));
            if (image == 0) {
                image = null;
            }
            String introduce = cursor.getString(cursor.getColumnIndex("introduce"));
            Integer agree = cursor.getInt(cursor.getColumnIndex("agree"));
            Integer comment = cursor.getInt(cursor.getColumnIndex("comment"));
            RecommendEntity recommendEntity = new RecommendEntity(title, author, concern, header, image, introduce, agree, comment);
            recommendEntities.add(recommendEntity);
        }
        cursor.close();
        db.close();
        recommendAdapter.setRecommendEntities(recommendEntities);
        recommendAdapter.notifyDataSetChanged();
        NormalLog.log(this.getClass(), 2, "getInfo", 1);
    }
}
