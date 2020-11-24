package com.six.zhihu.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.dao.DBOpenHelper;

public class MyActivity extends BaseActivity {
    TextView profile;
    private TextView my_name;
    private DBOpenHelper helper;
    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(),2,"initLayout",2, R.layout.fragment_my);
        helper = new DBOpenHelper(this);
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
        my_name = findViewById(R.id.my_name);
        SQLiteDatabase db;
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        my_name.setText(cursor.getString(2));
        NormalLog.log(this.getClass(),2,"initView",1);

    }

    @Override
    protected void initData() {
        NormalLog.log(this.getClass(),2,"initData",1);

    }
}
