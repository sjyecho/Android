package com.example.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
        dbHelper=new MyDatabaseHelper(this,"myDict.db3",1);
        insert=findViewById(R.id.insert);
        search=findViewById(R.id.search);
        insert.setOnClickListener(view -> {
            String word = ((EditText) findViewById(R.id.word)).getText().toString();
            String detail = ((EditText) findViewById(R.id.detail)).getText().toString();
            //插入生词记录
            insertData(dbHelper.getReadableDatabase(),word,detail);
            //显示提示信息
            Toast.makeText(Dict.this, "添加生词成功", Toast.LENGTH_SHORT).show();
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入
                String key = ((EditText)findViewById(R.id.key)).getText().toString();
                //执行查询

            }
        });
    }

    private void insertData(SQLiteDatabase db,String title,String content){
        db.execSQL("insert into news_inf values(null,?,?)",new String[]{title,content});
    }
}
