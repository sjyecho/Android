package com.example.demo01.Demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.demo01.R;

/**
 * 跟随手指的小球
 */
@SuppressWarnings("all")
public class demo03 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //获取布局文件中的LinearLayout容器
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        //创建DrawView组件
        DrawView draw = new DrawView(this);
        //设置自定义组件的最大宽度、高度
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);
        //为draw组件绑定Touch事件
        draw.setOnTouchListener(new View.OnTouchListener() {
            /*
                此处产生警告：
                @SuppressLint("ClickableViewAccessibility")
                这个警告是说,有可能会和点击事件发生冲突
                如果你在touch中返回了true,那么就不会响应onClick事件了
            */
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {//监听触摸屏事件的监听器
                //修改draw组件的currentX、currentY两个属性
                draw.currentX = motionEvent.getX();
                draw.currentY = motionEvent.getY();
                //通知draw组件重绘
                draw.invalidate();
                //返回true表明处理方法已经处理该事件
                return true;
            }
        });
        //向容器中添加组件
        root.addView(draw);
    }
}
