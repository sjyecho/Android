package com.example.demo01.Demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.demo01.R;

/**
 * 使用XML布局文件和Java代码混合控制UI界面
 */
public class demo02 extends Activity {

    //定义一个访问图片的数组
    int[] images = new int[]{
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
    };

    int currentImg = 0;//计数器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //获取LinearLayout布局容器
        LinearLayout main = (LinearLayout) findViewById(R.id.root);
        //程序创建Imageiew组件
        final ImageView image = new ImageView(this);
        //将ImageView组件添加到LinearLayout布局容器中
        main.addView(image);
        //初始化时显示第一张图片
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                image.setImageResource(images[++currentImg]);
            }
        });
    }
}
