package com.example.kaohedemo4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateLXR extends Activity {

    MyDatabaseHelper dbHelper;
    Button insert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createlxr);
        dbHelper = new MyDatabaseHelper(this, "lianxiren.db3", 1);
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(view -> {
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String phonenumber = ((EditText) findViewById(R.id.phonenumber)).getText().toString();
            //插入生词记录
            insertData(dbHelper.getWritableDatabase(), name, phonenumber);
            //显示提示信息
            Toast.makeText(CreateLXR.this, "添加联系人成功", Toast.LENGTH_SHORT).show();
        });
    }

    protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        //遍历Cursor结果集
        while (cursor.moveToNext()) {
            //将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(1));
            map.put("phonenumber", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void insertData(SQLiteDatabase db, String name, String phonenumber) {
        db.execSQL("insert into lianxiren values(null,?,?)", new String[]{name, phonenumber});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序时关闭MyDatabaseHelper里的SQLiteDatabase
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
