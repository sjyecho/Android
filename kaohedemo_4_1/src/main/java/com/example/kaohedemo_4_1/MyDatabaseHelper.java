package com.example.kaohedemo_4_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    final String CREATE_CONTACTS_SQL = "create table contacts(_id integer primary key autoincrement,name,phone)";
    final String CREATE_COLLENTIONS_SQL = "create table collections(_id integer primary key autoincrement,name,phone)";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_SQL);
        sqLiteDatabase.execSQL(CREATE_COLLENTIONS_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
