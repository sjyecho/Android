package com.example.control_listener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterForListView extends Activity {

    private static final String TAG = "sjy";

    private String[] names = new String[]{
            "头像1", "头像2", "头像3", "头像4"
    };

    private int[] imageIds = new int[]{
            R.drawable.toux1, R.drawable.toux2, R.drawable.toux3, R.drawable.toux4,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpleadapter_for_listview);
        //创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItems.add(listItem);
        }
        //[{personName=头像1, header=2131165332}, {personName=头像2, header=2131165333}, {personName=头像3, header=2131165334}, {personName=头像4, header=2131165335}]
        Log.d(TAG,listItems.toString());

        //创建一个SimpleAdapter
        /*
            第二个参数：该参数应该是一个List<Map<String, Object>>类型的集合对象
            第三个参数：界面布局的id
            第四个参数：该参数应该是一个String[]类型的参数，该参数决定提取Map<String,？>对象中那些key对应的value来生成列表项
            第五个参数：该参数应该是一个int[]类型的参数，该参数决定使用哪些View组件来组合成一个列表项
        */
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                listItems,
                R.layout.simpleadapter_for_listview,
                new String[]{"personName", "header"},
                new int[]{R.id.name, R.id.header});

        ListView list = findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);
    }
}
