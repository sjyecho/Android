package com.example.demo01.Demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.demo01.R;

/**
 * 用编程式的方式开发UI界面
 */
public class demo01 extends Activity {

    //当第一次创建该Activity时回调该方法
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建一个线性布局管理器
        LinearLayout layout = new LinearLayout(this);
        //设置该Activity显示layout
        super.setContentView(layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        final TextView show = new TextView(this);
        Button bn = new Button(this);
        bn.setText(R.string.app_name);
        //ViewGroup.LayoutParams的两个参数对应视图组件的宽和高
        bn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //向Layout容器中添加TextView
        layout.addView(show);
        //向Layout容器中添加按钮
        layout.addView(bn);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText("hello,android," + new java.util.Date());
            }
        });
    }
}
