package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.File;

public class DBTest extends AppCompatActivity {

    SQLiteDatabase db;
    Button bn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建或打开数据库（此处需要使用绝对路径）,第二个参数是指定SQLiteDatabase.CursorFactory参数，该参数是一个用于返回Cursor的工厂，若置null，则使用默认的工厂
        ///data/data/com.example.sqlite/files/my.db3
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/my.db3", null);
        listView = findViewById(R.id.show);
        bn = findViewById(R.id.ok);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入
                String title = ((EditText) findViewById(R.id.title)).getText().toString();
                String content = ((EditText) findViewById(R.id.content)).getText().toString();

                if (!"".equals(title) && !"".equals(content)) {
                    try {
                        insertData(db, title, content);
                        Cursor cursor = db.rawQuery("select * from news_inf", null);
                        inflateList(cursor);
                    } catch (Exception e) {
                        //使用SimpleCursorAdapter封装Cursor时要求底层数据表的主键列的列名为_id，因为SimpleCursorAdapter只能识别列名为_id的主键。
                        // 否则会出现java.lang.IllegalArgumentException:column '_id' does not exist
                        db.execSQL("create table news_inf(_id integer primary key autoincrement," + "news_title varchar(50)," + "news_content varchar(255))");
                        //执行insert语句插入数据
                        insertData(db, title, content);
                        //执行查询
                        Cursor cursor = db.rawQuery("select * from news_inf", null);
                        inflateList(cursor);
                        ((EditText) findViewById(R.id.title)).setText("");
                        ((EditText) findViewById(R.id.content)).setText("");
                    }
                }

            }
        });

    }

    private void insertData(SQLiteDatabase db, String title, String content) {
        //执行插入语句
        db.execSQL("insert into news_inf values(null,?,?)", new String[]{title, content});
    }

    private void inflateList(Cursor cursor) {
        //填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(DBTest.this,
                R.layout.line,
                cursor,
                new String[]{"news_title", "news_content"},
                new int[]{R.id.my_title, R.id.my_content});
        //显示数据
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序时删除数据库并关闭SQLiteDatabase
        if (db != null && db.isOpen()) {
            SQLiteDatabase.deleteDatabase(new File(db.getPath()));
            db.close();
        }
    }
}