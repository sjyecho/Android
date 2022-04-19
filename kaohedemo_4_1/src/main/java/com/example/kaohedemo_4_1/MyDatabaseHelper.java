package com.example.kaohedemo_4_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    final String CREATE_BOOK_SQL = "create table book(_id integer primary key autoincrement,sjmc,rksj,jg)";
    final String CREATE_TYPE_SQL = "create table type(_id integer primary key autoincrement,sjmc,lbmc)";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK_SQL);
        sqLiteDatabase.execSQL(CREATE_TYPE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
