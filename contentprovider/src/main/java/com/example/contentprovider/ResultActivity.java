package com.example.contentprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        //获取该intent所携带的数据
        Bundle data = intent.getExtras();
        //从Bundle数据包中取出数据
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable("data");
        //System.out.println(list);  [{word=12, detail=123}, {word=12, detail=4567}]

        SimpleAdapter adapter = new SimpleAdapter(
                ResultActivity.this,
                list,
                R.layout.item,
                new String[]{"word", "detail"},
                new int[]{R.id.word, R.id.detail});
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
