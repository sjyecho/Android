package com.example.kaohedemo_4_1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DemoProvider extends ContentProvider {

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MyDatabaseHelper dbHelpBook = null;
    private MyDatabaseHelper dbHelpType = null;

    static {
        uriMatcher.addURI(StaticUri.AUTHORITY, "bookquery", 1);
        uriMatcher.addURI(StaticUri.AUTHORITY, "bookdelete", 2);
        uriMatcher.addURI(StaticUri.AUTHORITY, "bookupdate", 3);
        uriMatcher.addURI(StaticUri.AUTHORITY, "bookinsert", 4);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typequery", 5);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typedelete", 6);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typeupdate", 7);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typeinsert", 8);
    }

    @Override
    public boolean onCreate() {

        dbHelpBook = new MyDatabaseHelper(getContext(), "book.db3", 1);
        ContentValues valuesbook = new ContentValues();
        for (int i = 1; i < 11; i++) {
            valuesbook.put(StaticUri.TableColumns.BOOK_SJMC, "书籍" + i);
            valuesbook.put(StaticUri.TableColumns.BOOK_RKSJ, "2012-11-15");
            valuesbook.put(StaticUri.TableColumns.BOOK_JG, "50" + i + "$");
            insert(StaticUri.TableColumns.BOOK_INSERT, valuesbook);
        }

        dbHelpType = new MyDatabaseHelper(getContext(), "type.db3", 1);
        ContentValues valuestype = new ContentValues();
        for (int i = 1; i < 5; i++) {
            valuestype.put(StaticUri.TableColumns.TYPE_SJMC, "图书" + i + i);
            valuestype.put(StaticUri.TableColumns.TYPE_LBMC, "历史");
            insert(StaticUri.TableColumns.TYPE_INSERT, valuestype);
        }


//        if (dbHelpType==null){
//            dbHelpType = new MyDatabaseHelper(getContext(), "type.db3", 1);
//            ContentValues valuestype = new ContentValues();
//
//        }
//        dbHelp = new MyDatabaseHelper(getContext(), "demotable.db3", 1);
//        ContentValues values = new ContentValues();
//        values.put(StaticUri.TableColumns.NAME, "张三");
//        values.put(StaticUri.TableColumns.PHONE, "110");
//        insert(StaticUri.TableColumns.CONTENT_INSERT, values);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = dbHelpBook.getReadableDatabase();
        SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 1:
                return db.query("book", strings, s, strings1, null, null, s1);
            case 5:
                return db2.query("type", strings, s, strings1, null, null, s1);
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelpBook.getReadableDatabase();
        //SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 4:
                db.insert("book", StaticUri.TableColumns.BOOK_ID, contentValues);
            case 8:
                //db2.insert("type", StaticUri.TableColumns.TYPE_ID, contentValues);
        }
//        if (rowId>0){
//            Uri tableUri = ContentUris.withAppendedId(uri, rowId);
//            getContext().getContentResolver().notifyChange(tableUri,null);
//            return tableUri;
//        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelpBook.getReadableDatabase();
        SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        //记录所删除的记录数
        int num = 0;
        //对uri进行匹配
        switch (uriMatcher.match(uri)) {
            case 2:
                num = db.delete("book", s, strings);
                break;
            case 3:
                db2.delete("type", s, strings);
                break;
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelpBook.getWritableDatabase();
        SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        //记录所修改的记录数
        int num;
        switch (uriMatcher.match(uri)) {
            case 3:
                num = db.update("book", contentValues, s, strings);
                break;
            case 7:
                num = db2.update("book", contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            //如果操作的数据是多项记录
            case 1:
                return "vnd.android.cursor.dir/com.example.dict";
            case 2:
                return "vnd.android.cursor.item/com.example.dict";
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
    }


}
