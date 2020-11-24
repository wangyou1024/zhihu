package com.six.zhihu.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.six.zhihu.R;

public class LoginActivity extends BaseActivity {

    private Button btn_count_pwd ;
    private EditText ed_count;
    private EditText ed_pwd;
    private String Tag = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        btn_count_pwd = findViewById(R.id.count_password_login);
        ed_count = findViewById(R.id.count_pwd_count);
        ed_pwd = findViewById(R.id.count_pwd_password);
        btn_count_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Tag, "点击登录按钮");
                if (ed_count.getText().toString().equals("1234567") && ed_pwd.getText().toString().equals("1234567")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d(Tag, "登录成功，进入主页面");
                }
                else {
                    Toast.makeText(getApplicationContext(),"账号或密码错误",Toast.LENGTH_LONG).show();
                    Log.d(Tag, "账号或密码错误，登录失败");
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
