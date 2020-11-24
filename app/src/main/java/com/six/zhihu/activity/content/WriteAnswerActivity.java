package com.six.zhihu.activity.content;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.six.zhihu.R;
import com.six.zhihu.activity.BaseActivity;

public class WriteAnswerActivity extends BaseActivity implements View.OnClickListener {
    // 取消按钮
    private ImageView ivBackContent;
    // 发送消息按钮
    private ImageView ivSendAnswer;
    // 回答的内容
    private EditText etAnswer;
    // 可发送的图片
    private Integer send = R.mipmap.send;
    private Integer sendDisabled = R.mipmap.send_disabled;

    private static final String TAG = "WriteAnswerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start...");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate end...");
    }

    @Override
    protected int initLayout() {
        Log.d(TAG, "initLayout start...");
        return R.layout.activity_write_answer;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView start...");
        ivBackContent = findViewById(R.id.back_content);
        ivSendAnswer = findViewById(R.id.send_answer);
        etAnswer = findViewById(R.id.answer);

        ivBackContent.setOnClickListener(this);
        ivSendAnswer.setOnClickListener(this);
        etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "before text change...");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "on text changed start...");
                // 在内容发生改变的时候改变发送图标的样式
                if (count > 0) {
                    ivSendAnswer.setImageResource(send);
                } else {
                    ivSendAnswer.setImageResource(sendDisabled);
                }
                Log.d(TAG, "on text changed end...");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "after text changed...");
            }
        });

        Log.d(TAG, "initView end...");
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_content:
                // 返回操作
                Log.d(TAG, "返回上一个页面start...");
                setResult(0);
                finish();
                Log.d(TAG, "返回上一个页面end...");
                break;
            case R.id.send_answer:
                Log.d(TAG, "发布回答start...");
                // 发布信息
                setResult(1);
                Toast.makeText(this, "回答成功...", Toast.LENGTH_SHORT).show();
                finish();
                Log.d(TAG, "发布回答end...");
                break;
            default: break;
        }
    }
}