package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;

public class DemoAutoCompleteTextView extends Activity {

    String[] books = new String[]{
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
        ArrayAdapter<String> aa = new ArrayAdapter<>(getApplication(), android.R.layout.simple_dropdown_item_1line, books);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        actv.setAdapter(aa);
    }
}
