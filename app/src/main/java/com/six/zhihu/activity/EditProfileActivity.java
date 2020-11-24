package com.six.zhihu.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.dao.DBOpenHelper;
import com.six.zhihu.entity.User;

public class EditProfileActivity extends BaseActivity implements View.OnClickListener{
    TextView save;
    ImageView back2Profile;
    DBOpenHelper helper;
    EditText editTextTextPersonName;
    EditText editTextTextPersonName2;
    EditText editTextTextPersonName3;
    EditText editTextTextPersonName4;
    EditText editTextTextPersonName5;
    EditText editTextTextPersonName6;

    @Override
    protected int initLayout() {
        NormalLog.log(this.getClass(),2,"initLayout",2, R.layout.edit_profile);
        helper = new DBOpenHelper(this);
        return R.layout.edit_profile;
    }

    @Override
    protected void initView() {
        NormalLog.log(this.getClass(),2,"initView",0);
        back2Profile = findViewById(R.id.back_2_profile);
        back2Profile.setOnClickListener((view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            this.finish();
        }));
        save = findViewById(R.id.save);
        save.setOnClickListener(this);
        NormalLog.log(this.getClass(),2,"initView",1);


    }

    @Override
    protected void initData() {
        NormalLog.log(this.getClass(),2,"initView",0);
        SQLiteDatabase db;
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        User user = new User();
        user.setName(cursor.getString(2));
        user.setDetail(cursor.getString(3));
        user.setSex(cursor.getString(4));
        user.setHometown(cursor.getString(5));
        user.setWork(cursor.getString(6));

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);

        editTextTextPersonName.setText(cursor.getString(2));
        editTextTextPersonName2.setText(cursor.getString(3));
        editTextTextPersonName3.setText(cursor.getString(4));
        editTextTextPersonName4.setText(cursor.getString(5));
        editTextTextPersonName5.setText(cursor.getString(6));
        editTextTextPersonName6.setText(cursor.getString(7));
        while (cursor.moveToNext()){
            editTextTextPersonName.setText(cursor.getString(2));
            editTextTextPersonName2.setText(cursor.getString(3));
            editTextTextPersonName3.setText(cursor.getString(4));
            editTextTextPersonName4.setText(cursor.getString(5));
            editTextTextPersonName5.setText(cursor.getString(6));
            editTextTextPersonName6.setText(cursor.getString(7));
        }
        cursor.close();
        db.close();

        NormalLog.log(this.getClass(),2,"initView",1);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String name;
        String detail;
        String sex;
        String birth;
        String hometown;
        String work;
        ContentValues values = new ContentValues();
        values.put("name",name=editTextTextPersonName.getText().toString());
        values.put("detail",name=editTextTextPersonName2.getText().toString());
        values.put("sex",name=editTextTextPersonName3.getText().toString());
        values.put("birth",name=editTextTextPersonName4.getText().toString());
        values.put("hometown",name=editTextTextPersonName5.getText().toString());
        values.put("work",name=editTextTextPersonName6.getText().toString());
        db.update("user",values,null,null);
        db.close();
        Toast.makeText(this,"已保存",Toast.LENGTH_SHORT).show();


    }
}
