package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.UserDictionary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DictProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static {
        //为UriMatcher注册两个Uri
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }

    //第一次调用该DictProvider时，系统先创建DictProvider对象，并回调该方法
    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDatabaseHelper(getContext(), "myDict.db3", 1);
        return true;
    }

    //查询数据的方法
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case WORDS:
                //执行查询
                return db.query("dict", projection, selection, selectionArgs, null, null, sortOrder);
            case WORD:
                //解析出想查询的记录ID
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (selection != null && !"".equals(selection)) {
                    where = where + "and" + selection;
                }
                return db.query("dict", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
    }

    //插入数据方法
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //获得数据库实例
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        //插入数据,返回行ID
        long rowId = db.insert("dict", Words.Word._ID, contentValues);
        //如果插入成功则返回uri
        if (rowId > 0) {
            //在已有的Uri的后面追加ID数据（withAppendedId用于为路径加上ID部分）
            Uri wordUri = ContentUris.withAppendedId(uri, rowId);
            //通知数据已经改变
            getContext().getContentResolver().notifyChange(wordUri, null);
            return wordUri;
        }
        return null;
    }

    //删除数据的方法
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        //记录所删除的记录数
        int num = 0;
        //对uri进行匹配
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.delete("dict", s, strings);
                break;
            case WORD:
                //解析出所需要删除的记录ID
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (s != null && !s.equals("")) {
                    where = where + "and" + s;
                }
                num = db.delete("dict", where, strings);
                break;
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    //修改数据的方法
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        //记录所修改的记录数
        int num = 0;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.update("dict", contentValues, s, strings);
                break;
            case WORD:
                //解析出想修改的记录ID
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (s != null && !s.equals("")) {
                    where = where + "and" + strings;
                }
                num = db.update("dict", contentValues, where, strings);
                break;
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    //返回指定uri参数对应的数据的MIME类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            //如果操作的数据是多项记录
            case WORDS:
                return "vnd.android.cursor.dir/com.example.dict";
            case WORD:
                return "vnd.android.cursor.item/com.example.dict";
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
    }

}

