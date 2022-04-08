package com.example.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
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
 * 点击列表项会弹出选择框
 * 可使用BaseAdapter
 */
public class DemoSpinner extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                //指定一共包含十个选项
                return 10;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            /*//重写该方法，方法返回的view将作为列表框的每项
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                TextView text=new TextView(DemoSpinner.this);
                text.setText(position+" ");
                text.setTextSize(20);

                *//*
                    此处产生@SuppressLint("ResourceAsColor")
                    因为R.color.red是一个资源文件，可能会set失败
                *//*
                text.setTextColor(R.color.red);
                return text;
            }*/

            /*
                getView返回一个LinearLayout对象
                此LinearLayout对象里先有一个ImageView，后面还有一个TextView
                即Spinner的每个列表项都是LinearLayout对象
            */
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LinearLayout line=new LinearLayout(DemoSpinner.this);
                line.setOrientation(LinearLayout.HORIZONTAL);
                ImageView image=new ImageView(DemoSpinner.this);
                image.setImageResource(R.drawable.ic_launcher_foreground);
                TextView text=new TextView(DemoSpinner.this);
                text.setText(position+"");
                text.setTextSize(20);
                text.setTextColor(R.color.red);
                line.addView(image);
                line.addView(text);
                return line;
            }
        };

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(ba);
    }
}
