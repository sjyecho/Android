package com.example.app2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("all")
public class FrameLayoutTest extends Activity {

    private int currentColor = 0;
    int[] colors = new int[]{
            R.color.black,
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700,
            R.color.teal_200,
            R.color.teal_700,
            R.color.white
    };
    final int[] names = new int[]{
            R.id.View01,
            R.id.View02,
            R.id.View03,
            R.id.View04,
            R.id.View05,
            R.id.View06,
            R.id.View07
    };
    TextView[] views = new TextView[7];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        for (int i = 0; i < 7; i++) {
            views[i] = (TextView) findViewById(names[i]);
        }
        final Handler handler=new Handler(){
            /*
            此处存在警告:
            @SuppressLint(“HandlerLeak”)
            原因：Handler在Android中用于消息的发送与异步处理,
            常常在Activity中作为一个匿名内部类来定义，
            此时Handler会隐式地持有一个外部类对象（通常是一个Activity）的引用。
            当Activity已经被用户关闭时，由于Handler持有Activity的引用造成Activity无法被GC回收，这样容易造成内存泄露
            */
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what==0x1122){
                    for(int i=0;i<7-currentColor;i++){
                        views[i].setBackgroundResource(colors[i+currentColor]);
                    }
                    for(int i=7-currentColor,j=0;i<7;i++,j++){
                        views[i].setBackgroundResource(colors[j]);
                    }
                }
                super.handleMessage(msg);
            }
        };
        //定义一个线程周期性地改变currentColor变量值
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currentColor++;
                if (currentColor>=6){
                    currentColor=0;
                }
                //发送一条消息通知系统改变7个TextView组件的背景色
                Message m=new Message();
                //给该消息定义一个标识
                m.what=0x1122;
                handler.sendMessage(m);
            }
        },0,100);//0.1秒执行一次的任务
    }
}
