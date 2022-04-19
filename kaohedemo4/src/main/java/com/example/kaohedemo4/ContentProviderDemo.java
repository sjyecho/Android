package com.example.kaohedemo4;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProviderDemo extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int C = 1;//create
    private static final int R = 2;//query
    private static final int U = 3;//update
    private static final int D = 4;//delete
    private MyDatabaseHelper dbOpenHelper;

    static {
        //为UriMatcher注册四个Uri
        matcher.addURI(ContentStatic.AUTHORITY, "name",C);
        matcher.addURI(ContentStatic.AUTHORITY, "name/#",R);
        matcher.addURI(ContentStatic.AUTHORITY, "name/#",U);
        matcher.addURI(ContentStatic.AUTHORITY, "name/#",D);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDatabaseHelper(getContext(), "lianxiren.db3", 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)){
            case R:
                return db.query("lianxiren",projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        db.insert("lianxiren",ContentStatic.Word.NAME, contentValues);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
