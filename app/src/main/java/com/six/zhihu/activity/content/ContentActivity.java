package com.six.zhihu.activity.content;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.six.zhihu.R;
import com.six.zhihu.activity.BaseActivity;
import com.six.zhihu.activity.utils.SizeUtils;
import com.six.zhihu.dao.ContentDao;
import com.six.zhihu.entity.content.ContentInfo;

import java.util.Formatter;

public class ContentActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ContentActivity";

    // 正文信息
    private TextView tvContent;
    // 头像
    private ImageView avatar;
    // 回答的数量标签
    private TextView tvAnswerNum;
    // 标题
    private TextView tvTitle;
    // 昵称
    private TextView tvNickName;
    // 签名
    private TextView tvSignature;
    // 喜欢
    private ImageView ivLove;
    private TextView tvLove;
    private Boolean isLove = false;
    // 收藏
    private ImageView ivStar;
    private TextView tvStar;
    private Boolean isStar = false;
    private TextView tvComment;

    // 赞同和反对
    private LinearLayout agreeOppose;
    private Boolean isClickable = false;

    private TextView agreeStr;

    private ContentDao contentDao;
    private ContentInfo contentInfo;

    // 记录不同状态下的喜欢和收藏
    private Integer unlove = R.mipmap.love_unselected;
    private Integer love = R.mipmap.love_selected;
    private Integer uncollection = R.mipmap.star_unselected;
    private Integer collection = R.mipmap.star_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start...");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate end...");
    }

    @Override
    protected int initLayout() {
        Log.d(TAG, "init layout start...");
        return R.layout.activity_content;
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView start...");
        tvContent = findViewById(R.id.content_page);
        tvAnswerNum = findViewById(R.id.content_answer_nums);
        tvTitle = findViewById(R.id.content_title);
        tvNickName = findViewById(R.id.nickname);
        tvSignature = findViewById(R.id.signature);
        avatar = findViewById(R.id.avatar);
        ivLove = findViewById(R.id.love);
        ivStar = findViewById(R.id.collection);
        tvStar = findViewById(R.id.collection_str);
        tvLove = findViewById(R.id.love_str);
        tvComment = findViewById(R.id.comment_str);
        agreeStr = findViewById(R.id.common_str);
        agreeOppose = findViewById(R.id.agree_oppose);

        findViewById(R.id.content_back).setOnClickListener(this);
        findViewById(R.id.write_answer).setOnClickListener(this);
        ivStar.setOnClickListener(this);
        ivLove.setOnClickListener(this);
        findViewById(R.id.agree).setOnClickListener(this);
        findViewById(R.id.oppose).setOnClickListener(this);
        agreeOppose.setOnClickListener(this);

        Log.d(TAG, "initView end...");
    }

    @Override
    protected void initData() {
        Log.d(TAG, "initData start...");
        Integer id = getIntent().getIntExtra("id", 1);
        // 设置喜欢和收藏的初始状态
        ivStar.setImageResource(uncollection);
        ivLove.setImageResource(unlove);
        // 获取头像
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avatar, null);
        // 设置头像
        avatar.setImageBitmap(bitmap);
        // 获取基本信息
        contentDao = new ContentDao(this);
        contentInfo = contentDao.query(id);
        tvTitle.setText(contentInfo.getTitle());
        tvSignature.setText(contentInfo.getSignature());
        tvContent.setText(contentInfo.getContent());
        tvAnswerNum.setText(setAnswerNum(contentInfo.getAnswerNum()));
        tvNickName.setText(contentInfo.getNickname());
        tvLove.setText(formatterNum(contentInfo.getLoveNum()));
        tvStar.setText(formatterNum(contentInfo.getCollectionNum()));
        tvComment.setText(formatterNum(contentInfo.getCommentNum()));
        Log.d(TAG, "initData end...");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.content_back) {
            Log.d(TAG, "返回...");
            finish();
        } else if (id == R.id.write_answer) {
            Log.d(TAG, "写回答...");
            Intent intent = new Intent(this, WriteAnswerActivity.class);
            startActivityForResult(intent, 1);
            Log.d(TAG, "点击事件完成...");
        } else if (id == R.id.love) {
            Log.d(TAG, "点击喜欢...");
            contentInfo.setLoveNum(isLove ? contentInfo.getLoveNum() - 1 : contentInfo.getLoveNum() + 1);
            ivLove.setImageResource(isLove ? unlove : love);
            isLove = !isLove;
            tvLove.setText(formatterNum(contentInfo.getLoveNum()));
            contentDao.updateLove(contentInfo.getLoveNum(), contentInfo.getId());
            Log.d(TAG, "点击事件完成...");
        } else if (id == R.id.collection) {
            Log.d(TAG, "点击收藏...");
            contentInfo.setCollectionNum(isStar ? contentInfo.getCollectionNum() - 1 : contentInfo.getCollectionNum() + 1);
            ivStar.setImageResource(isStar ? uncollection : collection);
            isStar = !isStar;
            tvStar.setText(formatterNum(contentInfo.getCollectionNum()));
            contentDao.updateCollection(contentInfo.getCollectionNum(), contentInfo.getId());
            Log.d(TAG, "点击事件完成...");
        } else if (id == R.id.agree && !isClickable) {
            Log.d(TAG, "赞同...");
            contentInfo.setAgreeNum(contentInfo.getAgreeNum() + 1);

            agreeStr.setText(setAgreeNum(contentInfo.getAgreeNum()));

            isClickable = true;

            findViewById(R.id.oppose).setVisibility(View.INVISIBLE);
            contentDao.updateAgreeNum(contentInfo.getAgreeNum(), contentInfo.getId());
            Log.d(TAG, "点击事件完成...");
        } else if (id == R.id.oppose && !isClickable) {
            Log.d(TAG, "反对...");
            agreeStr.setText("已反对");

            isClickable = true;

            findViewById(R.id.agree).setVisibility(View.INVISIBLE);
            Log.d(TAG, "点击事件完成...");
        } else if (id == R.id.agree_oppose && isClickable) {
            Log.d(TAG, "切换赞同和反对的页面...");

            if ("已反对".contentEquals(agreeStr.getText())) {
                findViewById(R.id.agree).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.oppose).setVisibility(View.VISIBLE);
                // 修改数据库回之前的状态
                contentInfo.setAgreeNum(contentInfo.getAgreeNum() - 1);
                contentDao.updateAgreeNum(contentInfo.getAgreeNum(), contentInfo.getId());
            }

            agreeStr.setText("赞同");
            isClickable = false;
            Log.d(TAG, "点击事件完成...");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                Log.d(TAG, "回答数量+1...");
                contentInfo.setAnswerNum(contentInfo.getAnswerNum() + 1);
                tvAnswerNum.setText(setAnswerNum(contentInfo.getAnswerNum()));
                contentDao.addAnswer(contentInfo.getAnswerNum(), contentInfo.getId());
                Log.d(TAG, "存储完成...");
            }
        }
    }

    private String setAgreeNum(Integer agreeNum) {
        return "赞同 " + formatterNum(agreeNum);
    }

    /**
     * 对回答数量进行处理
     * @param answerNum
     * @return
     */
    private String setAnswerNum(Integer answerNum) {
        return "知乎·" + formatterNum(answerNum) + "个回答";
    }

    /**
     * 对返回的数字进行排版成需要的样式
     * @param num
     * @return
     */
    private String formatterNum(Integer num) {
        Formatter f = new Formatter();
        if (num < 1000) {
            f.format("%d", num);
        } else if (num < 10000) {
            f.format("%d,%s", num / 1000, String.valueOf(num).substring(1));
        } else {
            f.format("%d.%d万", num / 10000, num / 1000 - num / 10000 * 10);
        }
        return f.toString();
    }
}