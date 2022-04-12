package com.example.control_listener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

/**
 * 以垂直列表的形式显示所有列表项
 * 两种创建ListView的两种方式：
 * 1.直接使用ListView创建
 * 2.让Activity继承ListActivity
 * <p>
 * ListView常用xml属性：
 * <p>
 * android:choiceMode                  设置ListView的选择行为
 * android:divider                     设置List列表项的分隔条(既可用颜色分割，也可用Drawable分割)
 * android:dividerHeight               设置分隔条的高度
 * android:entries                     指定一个数组资源，Android将根据该数组资源生成ListView
 * android:footerDividersEnabled       如果设置为false，则不在footer View之前绘制分隔条
 * android:headerDividersEnabled       如果设置为false，则不在header View之后绘制分隔条
 */
public class ListViewDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_demo);
        ListView list = findViewById(R.id.list2);
        String[] arr = {"数学", "语文", "英语"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        list.setAdapter(arrayAdapter);
    }
}
