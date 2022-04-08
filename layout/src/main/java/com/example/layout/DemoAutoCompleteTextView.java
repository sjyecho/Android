package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;

/**
 * 当用户输入一定字符之后，文本框会显示一个下拉菜单，供用户从中选择
 * 当用户选择某个菜单项之后，AutoCompleteTextView会自动填充
 */
public class DemoAutoCompleteTextView extends Activity {

    private static final String[] books = new String[]{
            "高等数学",
            "操作系统",
            "数据结构",
            "体系结构"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocompletetextview);
        //创建一个ArrayAdapter，封装数组
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, books);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        actv.setAdapter(aa);
    }
}
