package com.six.zhihu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.six.zhihu.entity.content.ContentInfo;

public class ContentDao {
    private DBOpenHelper helper;

    public ContentDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void updateLove(Integer loveNum, Integer id) {
        Log.d("ContentDao", "update love nums start...");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("love_num", loveNum);
        int count = db.update("content", values, "id=?",
                new String[]{id + ""});
        Log.d("ContentDao", "update SUCCESS, count is '" + count + "'");
        db.close();
        Log.d("ContentDao", "update love nums end...");
    }

    public void addAnswer(Integer answerNum, Integer id) {
        Log.d("ContentDao", "add answer start...");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("answer_num", answerNum);
        int count = db.update("content", values, "id=?",
                new String[]{id + ""});
        Log.d("ContentDao", "update SUCCESS, count is '" + count + "'");
        db.close();
        Log.d("ContentDao", "add answer end...");
    }

    public void updateAgreeNum(Integer agreeNum, Integer id) {
        Log.d("ContentDao", "update agree nums start...");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("agree_num", agreeNum);
        int count = db.update("content", values, "id=?",
                new String[]{id + ""});
        Log.d("ContentDao", "update SUCCESS, count is '" + count + "'");
        db.close();
        Log.d("ContentDao", "update agree nums end...");
    }

    public void updateCollection(Integer collectionNum, Integer id) {
        Log.e("ContentDao", "joidpg" + id);
        Log.d("ContentDao", "update collection nums start...");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("collection_num", collectionNum);
        int count = db.update("content", values, "id=?",
                new String[]{id + ""});
        Log.d("ContentDao", "update SUCCESS, count is '" + count + "'");
        db.close();
        Log.d("ContentDao", "update collection nums end...");
    }

    public ContentInfo query(Integer id) {
        Log.d("ContentDao", "query by id start...");
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from content where id=" + id + "", null);
        cursor.moveToFirst();

        int idNum = cursor.getColumnIndex("id");
        int titleId = cursor.getColumnIndex("title");
        int answerNumId = cursor.getColumnIndex("answer_num");
        int nicknameId = cursor.getColumnIndex("nickname");
        int signatureId = cursor.getColumnIndex("signature");
        int contentId = cursor.getColumnIndex("content");
        int agreeNumId = cursor.getColumnIndex("agree_num");
        int loveNumId = cursor.getColumnIndex("love_num");
        int commentNumId = cursor.getColumnIndex("comment_num");
        int collectionNumId = cursor.getColumnIndex("collection_num");

        ContentInfo contentInfo = new ContentInfo(
                cursor.getInt(idNum),
                cursor.getString(titleId),
                cursor.getString(nicknameId),
                cursor.getString(signatureId),
                cursor.getInt(answerNumId),
                cursor.getInt(agreeNumId),
                cursor.getString(contentId),
                cursor.getInt(loveNumId),
                cursor.getInt(commentNumId),
                cursor.getInt(collectionNumId)
        );

        cursor.close();
        db.close();

        Log.d("ContentDao", "query raw end...");

        return contentInfo;
    }
}
