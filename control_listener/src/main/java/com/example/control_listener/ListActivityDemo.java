package com.example.control_listener;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

/**
 * 继承ListActivity实现ListView
 */
public class ListActivityDemo extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] arr = {"数学", "语文", "英语"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,arr);
        setListAdapter(adapter);
    }
}
