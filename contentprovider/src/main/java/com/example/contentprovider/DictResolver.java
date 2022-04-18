package com.example.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictResolver extends Activity {

    ContentResolver contentResolver;
    Button insert;
    Button search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictresolver);
        //获取系统的ContentResolver对象
        contentResolver = getContentResolver();
        insert=findViewById(R.id.insert);
        search=findViewById(R.id.search);
        //为insert按钮的单击事件绑定监听
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入
                String word=((EditText)findViewById(R.id.word)).getText().toString();
                String detail=((EditText)findViewById(R.id.detail)).getText().toString();
                //插入生词记录
                ContentValues values=new ContentValues();
                values.put(Words.Word.WORD,word);
                values.put(Words.Word.DETAIL,detail);
                contentResolver.insert(Words.Word.DICT_CONTENT_URI,values);
                //显示提示信息
                Toast.makeText(DictResolver.this, "添加生词成功", Toast.LENGTH_SHORT).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入
                String key=((EditText)findViewById(R.id.key)).getText().toString();
                //执行查询
                Cursor cursor=contentResolver.query(Words.Word.DICT_CONTENT_URI,null,"word like ? or detail like ?",new String[]{"%"+key+"%","%"+key+"%"},null);
                //创建一个Bundle对象
                Bundle data=new Bundle();
                data.putSerializable("data",converCursorToList(cursor));
                Intent intent=new Intent(DictResolver.this,ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Map<String,String>> converCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> result=new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            //取出查询记录中第二列，第三列的值
            map.put(Words.Word.WORD,cursor.getString(1));
            map.put(Words.Word.DETAIL,cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}
