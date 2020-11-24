package com.six.zhihu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context) {
        super(context, "zhihu.db", null, 3);
    }

    // 修改此方法后需要删除原来的数据库文件，关闭虚拟机，关闭app，重新启动，否则不会运行
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        NormalLog.log(this.getClass(), 2, "onCreate", 0);
        sqLiteDatabase.execSQL("create table if not exists recommend (id integer primary key autoincrement," +
                "title text,author text, concern text,header integer,image integer,introduce text,agree integer,comment integer)");
        sqLiteDatabase.execSQL("create table if not exists hottop (id integer primary key autoincrement," +
                "grade integer,title text, hot text, image integer) ");
        sqLiteDatabase.execSQL("create table if not exists dynamic (id integer primary key autoincrement," +
                "authorHeader integer, authorName text, updateTime text, title text, content text,agree integer,comment integer)");
        sqLiteDatabase.execSQL("create table if not exists concern(id integer primary key autoincrement," +
                "imageHeader integer,name text)");
        // ContentActivity所使用的页面
        sqLiteDatabase.execSQL("create table if not exists content(id integer primary key autoincrement," +
                "title text, answer_num integer, nickname text, signature text," +
                "content text, agree_num integer, love_num integer, comment_num integer, collection_num integer)");
        createRecommend(sqLiteDatabase);
        createHotTop(sqLiteDatabase);
        createDynamic(sqLiteDatabase);
        createConcernPerson(sqLiteDatabase);
        createContent(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int count(String name, SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) count from " + name, null);
        cursor.moveToNext();
        int count = cursor.getInt(cursor.getColumnIndex("count"));
        cursor.close();
        return count;
    }

    public void createRecommend(SQLiteDatabase sqLiteDatabase) {
        NormalLog.log(this.getClass(), 2, "createRecommend", 0);
        if (count("recommend", sqLiteDatabase) > 0) {
            NormalLog.log(this.getClass(), 2, "createRecommend", 1, 0);
            return;
        }
        for (int i = 0; i < 50; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", i + "有哪些值得大学生收藏的网站？");
            contentValues.put("header", R.drawable.shape_search);
            if (i % 2 == 0) {
                contentValues.put("image", R.mipmap.recommond_image);
            } else {
                contentValues.put("image", 0);
            }
            contentValues.put("author", "见长");
            contentValues.put("concern", "超过13.8万的用户关注了TA");
            contentValues.put("introduce", "大学时期时间相对充裕，在认真学习本专业的同时，学习更多的技能也是十分必要,学习更多的技能也是十分必要");
            contentValues.put("agree", 110000);
            contentValues.put("comment", 4);
            sqLiteDatabase.insert("recommend", null, contentValues);
        }
        NormalLog.log(this.getClass(), 2, "createRecommend", 1);
    }

    public void createHotTop(SQLiteDatabase sqLiteDatabase) {
        NormalLog.log(this.getClass(), 2, "createHotTop", 0);
        if (count("hottop", sqLiteDatabase) > 0) {
            NormalLog.log(this.getClass(), 2, "createHotTop", 1, 0);
            return;
        }
        for (int i = 0; i < 50; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("grade", i + 1);
            contentValues.put("title", "如何看待越来越多年轻人追捧[摸鱼哲学]，拒绝努力的年轻人真比老一辈活得更通透吗？");
            contentValues.put("image", R.mipmap.recommond_image);
            contentValues.put("hot", "2304万热度");
            sqLiteDatabase.insert("hottop", null, contentValues);
        }
        NormalLog.log(this.getClass(), 2, "createHotTop", 1);
    }

    public void createDynamic(SQLiteDatabase sqLiteDatabase) {
        NormalLog.log(this.getClass(), 2, "createDynamic", 0);
        if (count("dynamic", sqLiteDatabase) > 0) {
            NormalLog.log(this.getClass(), 2, "createDynamic", 1, 0);
            return;
        }
        for (int i = 0; i < 50; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("authorHeader", R.mipmap.header);
            contentValues.put("authorName", "张大人");
            contentValues.put("title", "Dubbo分布式架构搭建教育PC站-微信支付");
            contentValues.put("content", "创建二维码安装qrcodejs2（注意：安装的是qrcodejs2，不要安装qrcode，否则会报错）npm install qrcodejs2\n" +
                    "--save页面中引入《el-dialog:visible.sync=\"enadbojowefwfweiwjelksndfiwjkjsldjfow\"");
            contentValues.put("updateTime", "2020-6-6");
            contentValues.put("agree", 10);
            contentValues.put("comment", 14);
            sqLiteDatabase.insert("dynamic", null, contentValues);
        }
        NormalLog.log(this.getClass(), 2, "createDynamic", 1);
    }

    public void createConcernPerson(SQLiteDatabase sqLiteDatabase) {
        NormalLog.log(this.getClass(), 2, "createConcernPerson", 0);
        if (count("concern", sqLiteDatabase) > 0) {
            NormalLog.log(this.getClass(), 2, "createConcernPerson", 1, 0);
            return;
        }
        for (int i = 0; i < 10; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("imageHeader", R.mipmap.header);
            contentValues.put("name", "你好");
            sqLiteDatabase.insert("concern", null, contentValues);
        }
        NormalLog.log(this.getClass(), 2, "createConcernPerson", 1);

    }

    /**
     * ContentActivity所使用的数据库，初始化数据
     *
     * @param sqLiteDatabase
     */
    public void createContent(SQLiteDatabase sqLiteDatabase) {
        Log.d("createContent", "初始化数据");
        if (count("content", sqLiteDatabase) > 0) {
            Log.d("createContent", "已有数据，不需要再插入");
            return;
        }

        StringBuilder contentMsg = new StringBuilder();
        for (int i = 0; i < 30; ++i) {
            contentMsg.append("我是帅哥+").append(i).append("\n");
        }

        // 插入数据
        for (int i = 0; i < 20; ++i) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", "为什么这么帅?" + i);
            contentValues.put("answer_num", (int) (10 + Math.random() * (20000 - 10 + 1)));
            contentValues.put("nickname", "帅哥+" + i);
            contentValues.put("signature", "帅哥+++" + i);
            contentValues.put("content", contentMsg.toString());
            contentValues.put("agree_num", (int) (10 + Math.random() * (20000 - 10 + 1)));
            contentValues.put("love_num", (int) (10 + Math.random() * (20000 - 10 + 1)));
            contentValues.put("comment_num", (int) (10 + Math.random() * (20000 - 10 + 1)));
            contentValues.put("collection_num", (int) (10 + Math.random() * (20000 - 10 + 1)));
            sqLiteDatabase.insert("content", null, contentValues);
        }

        Log.d("createContent", "初始化数据完成");
    }
}
