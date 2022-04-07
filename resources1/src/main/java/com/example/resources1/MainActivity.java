package com.example.resources1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * 使用资源文件定义网格视图
 */
public class MainActivity extends AppCompatActivity {

    //使用字符串资源
    int[] textIds = new int[]{
            R.string.c1, R.string.c2, R.string.c3,
            R.string.c4, R.string.c5, R.string.c6,
            R.string.c7, R.string.c8, R.string.c9,
    };

    //使用颜色资源
    int[] colorIds = new int[]{
            R.color.c1, R.color.c2, R.color.c3,
            R.color.c4, R.color.c5, R.color.c6,
            R.color.c7, R.color.c8, R.color.c9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建一个BaseAdapter对象
        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                //要绑定的条目的数目
                return textIds.length;
            }

            @Override
            public Object getItem(int position) {
                //根据一个索引（位置）获得该位置的对象
                return getResources().getText(textIds[position]);
            }

            @Override
            public long getItemId(int position) {
                //获取条目的Id
                return position;
            }

            /**
             * 重写该方法，该方法返回的View将作为GridView的每个格子
             * @param position
             * @param view
             * @param viewGroup
             * @return
             */
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                TextView text = new TextView(MainActivity.this);
                Resources res = MainActivity.this.getResources();
                //使用尺寸资源来设置文本框的高度、宽度
                text.setWidth((int) res.getDimension(R.dimen.cell_width));
                text.setHeight((int) res.getDimension(R.dimen.cell_height));
                //使用字符串资源设置文本框内容
                text.setText(textIds[position]);
                //使用颜色资源来设置文本框的背景色
                text.setBackgroundResource(colorIds[position]);
                text.setTextSize(20);
                return text;
            }
        };

        GridView grid = (GridView) findViewById(R.id.grid01);
        //为GridView设置Adapter
        grid.setAdapter(ba);
    }
}