package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Main2Activity extends Activity {

    private Handler handler1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(() -> {

/*
            普通的线程是没有Looper的，如果需要Looper对象，那么必须要先调用Looper.prepare()方法，而且一个线程只能有一个Looper

            因为ActivityThread中的main()已经对Looper进行了prepare()操作，所以可以直接在主线程new Handler
            如果想在子线程中new Handler，则需要先手动调用Looper的prepare()方法初始化Looper，再调用Looper的loop()使Looper运转
*/
            Looper.prepare();
            handler1 = new MyHandler();
            Looper.loop();//调用此方法后，此方法会循环执行，从MessageQueue读取消息
            Log.e("child thread", "child thread end");
        }).start();
        while (handler1 == null) {

        }
        /*
            在主线程中向子线程中发送消息
        */
        handler1.sendEmptyMessage(0);
        handler1.getLooper().quitSafely();
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Log.e("child thread", "receive msg from main thread");
            }
        }
    }
}
