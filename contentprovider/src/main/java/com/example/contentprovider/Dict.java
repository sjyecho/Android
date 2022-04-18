package com.example.contentprovider;

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

public class Dict extends Activity {

    MyDatabaseHelper dbHelper;
    Button insert;
    Button search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dict);
        //创建MyDatabaseHelper对象，指定数据库版本为1，此处使用相对路径即可
        //数据库文件会自动保存在程序的数据文件夹的databases目录下
        dbHelper = new MyDatabaseHelper(this, "myDict.db3", 1);
        insert = findViewById(R.id.insert);
        search = findViewById(R.id.search);
        insert.setOnClickListener(view -> {
            String word = ((EditText) findViewById(R.id.word)).getText().toString();
            String detail = ((EditText) findViewById(R.id.detail)).getText().toString();
            //插入生词记录
            insertData(dbHelper.getWritableDatabase(), word, detail);
            //显示提示信息
            Toast.makeText(Dict.this, "添加生词成功", Toast.LENGTH_SHORT).show();
        });
        search.setOnClickListener(view -> {
            //获取用户输入
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            //执行查询
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from dict where word like ? or detail like ?",
                    new String[]{"%" + key + "%", "%" + key + "%"});
            //创建一个Bundle对象
            Bundle data = new Bundle();
            data.putSerializable("data", converCursorToList(cursor));
            //创建一个Intent
            Intent intent=new Intent(Dict.this,ResultActivity.class);
            intent.putExtras(data);
            startActivity(intent);
        });
    }

    protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        //遍历Cursor结果集
        while (cursor.moveToNext()) {
            //将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void insertData(SQLiteDatabase db, String word, String detail) {
        db.execSQL("insert into dict values(null,?,?)", new String[]{word, detail});
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
