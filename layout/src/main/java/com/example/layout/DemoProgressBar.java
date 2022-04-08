package com.example.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 定义一个进度条,用于向用户显示某个耗时操作完成的百分比
 */
public class DemoProgressBar extends Activity {

    //设置程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    //记录ProgressBar的完成进度
    int mProgressStatus = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        final ProgressBar bar = findViewById(R.id.bar);
        final ProgressBar bar2 = findViewById(R.id.bar2);
        //创建一个负责更新的进度的Handler
        @SuppressLint("HandlerLeak") final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //表明消息是由该程序发送的
                if (msg.what == 0x111) {
                    bar.setProgress(mProgressStatus);
                    bar2.setProgress(mProgressStatus);
                }
            }
        };
        //启动线程来执行任务
        new Thread() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    //获取耗时操作的完成百分比
                    mProgressStatus = doWork();
                    //发送消息到Handler
                    Message m = new Message();
                    m.what = 0x111;
                    mHandler.sendMessage(m);
                }
            }
        }.start();
    }

    public int doWork() {
        //为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
