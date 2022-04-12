package com.example.control_listener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Spinner就是一个列表选择框。相当于弹出一个菜单供用户选择
 * Spinner是ViewGroup的间接子类，因此也可以作为容器使用
 * <p>
 * XML属性：
 * android:prompt          设置该列表选择框的提示
 * android:entries         使用数组资源设置该下拉列表框的列表项目
 * <p>
 * Spinner的字体属性，无法在XMl中设置，可以使用适配器进行定义
 */
public class SpinnerDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_demo);
        Resources res = getResources();
        String[] books = res.getStringArray(R.array.books);

        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return books.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            /*@Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView text = new TextView(SpinnerDemo.this);
                text.setText(books[i]);
                text.setTextSize(20);
                text.setTextColor(getResources().getColor(R.color.c1));
                return text;
            }*/

            //创建一个LinearLayout，并向其中添加两个组件
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = new LinearLayout(SpinnerDemo.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView image = new ImageView(SpinnerDemo.this);
                image.setImageResource(R.drawable.ic_launcher_foreground);
                TextView text = new TextView(SpinnerDemo.this);
                text.setText(books[i] + i);
                text.setTextSize(20);
                text.setTextColor(getResources().getColor(R.color.c1));

                linearLayout.addView(image);
                linearLayout.addView(text);
                return linearLayout;
            }
        };
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(ba);
    }
}
