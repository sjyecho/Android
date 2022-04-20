package com.example.kaohedemo_4_1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DemoProvider extends ContentProvider {

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MyDatabaseHelper dbHelp = null;
    //private MyDatabaseHelper dbHelpType = null;

    static {
        uriMatcher.addURI(StaticUri.AUTHORITY, "query", 1);
        uriMatcher.addURI(StaticUri.AUTHORITY, "delete", 2);
        uriMatcher.addURI(StaticUri.AUTHORITY, "update", 3);
        uriMatcher.addURI(StaticUri.AUTHORITY, "insert", 4);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typequery", 5);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typedelete", 6);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typeupdate", 7);
        uriMatcher.addURI(StaticUri.AUTHORITY, "typeinsert", 8);
    }

    @Override
    public boolean onCreate() {

        dbHelp = new MyDatabaseHelper(getContext(), "contacts.db3", 1);
        Cursor cursor=query(StaticUri.TableColumns.CONTACTS_QUERY, null, "name like ? or phone like ?", new String[]{"%%", "%%"}, null);
        if (!cursor.moveToNext()){
            ContentValues values = new ContentValues();
            for (int i = 0; i < 2; i++) {
                values.put(StaticUri.TableColumns.CONTACTS_NAME, "姓名" + i);
                values.put(StaticUri.TableColumns.CONTACTS_PHONE, "17792936031");
                //valuesbook.put(StaticUri.TableColumns.BOOK_JG, "50" + i + "$");
                insert(StaticUri.TableColumns.CONTACTS_INSERT, values);
            }
        }else {
            return true;
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 1:
                return db.query("contacts", strings, s, strings1, null, null, s1);
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        //SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 4:
                db.insert("contacts", StaticUri.TableColumns.CONTACTS_ID, contentValues);
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
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        //SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        //记录所删除的记录数
        int num = 0;
        //对uri进行匹配
        switch (uriMatcher.match(uri)) {
            case 2:
                num = db.delete("contacts", "name = ?",strings);
                //System.out.println(strings[0]+"==========================================="+num);
                break;
            default:
                throw new IllegalArgumentException("未知Uri：" + uri);
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    /**
     *
     * @param uri
     * @param contentValues
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        //SQLiteDatabase db2 = dbHelpType.getReadableDatabase();
        //记录所修改的记录数
        int num;
        switch (uriMatcher.match(uri)) {
            case 3:
                // update info_tb set name ="xx" ,age=20,gender="男" where_id=2
                num = db.update("contacts", contentValues, "name = ?", selectionArgs);
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
