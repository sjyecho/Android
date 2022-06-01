package com.example.thread_to_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        new Thread(threadA).start();
        if (threadA.getmHandler() == null) {
            try {
                Thread.sleep(1000);
                handler = threadA.getmHandler();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(threadB).start();
    }

    //接收并处理消息
    class ThreadA implements Runnable {

        private Handler mHandler;

        public Handler getmHandler() {
            return mHandler;
        }

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                int i = 0;

                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            i++;
                            Log.e("线程A", "线程B发消息过来了------" + msg.obj + "已处理的消息数: " + i);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            };
            Looper.loop();
        }
    }

    //发送消息
    class ThreadB implements Runnable {
        int i = 0;

        @Override
        public void run() {
            while (true) {
                i++;
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "线程B--" + System.currentTimeMillis();
                handler.sendMessage(message);//handler就是ThreadA中的mHandler
                Log.d("线程A", "已发送的消息: " + i);
                if (i==10000){
                    break;
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
//            Message message = Message.obtain();
//            message.what = 1;
//            message.obj = "线程B--" + System.currentTimeMillis();
//            handler.sendMessage(message);//handler就是ThreadA中的mHandler
        }
    }
}