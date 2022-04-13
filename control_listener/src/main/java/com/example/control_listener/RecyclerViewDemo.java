package com.example.control_listener;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        RecyclerView recycler = findViewById(R.id.recycler);

        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> news = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            name.add("小白  " + i);
            news.add("今天搬砖了？");
            time.add("2021-01-03");
        }
        //Log.d("123","长度---------------"+name.size());
        //Log.d("123",name.toString());
        //适配器
        RecyclerAdapter adapter = new RecyclerAdapter(this, name, news, time);
        //布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置布局
        recycler.setLayoutManager(manager);
        //设置动画
        recycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        recycler.setAdapter(adapter);
    }
}
