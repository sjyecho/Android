package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ArrayAdapterList extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ListView list2 = findViewById(R.id.list2);
        //定义一个数组
        String[] arr = {"猪八戒", "孙悟空", "牛魔王"};
        //将数组包装到ArrayAdapter
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
        /*
        * 如果要使用 android.R.layout.simple_list_item_2
        * 布局文件的根节点必须是TextView
        * 使用android.R.layout.simple_list_item_1则不需要
        * */

        //为ListView设置Adapter
        list2.setAdapter(arrayAdapter);
    }
}
