package com.example.thread_to_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThreadA threadA=new ThreadA();
        ThreadB threadB=new ThreadB();
        new Thread(threadA).start();
        if (threadA.getmHandler()==null){
            try {
                Thread.sleep(1000);
                handler=threadA.getmHandler();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(threadB).start();
    }

    class ThreadA implements Runnable {

        private Handler mHandler;

        public Handler getmHandler() {
            return mHandler;
        }

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            Log.e("线程A", "线程B发消息过来了------"+msg.obj);
                            break;
                    }
                }
            };
            Looper.loop();
        }
    }

    class ThreadB implements Runnable {
        @Override
        public void run() {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = "线程B--" + System.currentTimeMillis();
            handler.sendMessage(message);//handler就是ThreadA中的mHandler
        }
    }
}