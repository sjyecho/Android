package com.example.app3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;

public class MainActivity10 extends Activity {

    //定义字符串数组，作为提示的文本
    String[] books=new String[]{
            "f疯狂A讲义",
            "f疯狂B讲义",
            "f疯狂C讲义",
            "f疯狂D讲义",
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main10);
        //创建一个ArrayAdapter，封装数组
        //适配器（adapter）在android中是数据和视图（View）之间的一个桥梁，通过适配器以便于数据在view视图上显示
        ArrayAdapter<String> aa=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,books);
        AutoCompleteTextView actv=(AutoCompleteTextView) findViewById(R.id.auto);
        //设置Adapter
        actv.setAdapter(aa);
    }
}
