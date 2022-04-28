package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * 异步消息处理流程
 * 由四部分组成：
 *              Message：在线程之间传递消息
 *              Handler：主要是用于发送和处理消息
 *              MessageQueue：存放所有通过Handler发送的消息，这部分消息会一直存在于消息队列中等待被处理
 *                            每个线程中只会有一个MessageQueue对象
 *              Looper：是每个线程中MessageQueue的管家
 *                      调用Looper的loop()方法后，就会进入无线循环中，每当发现MessageQueue中存在一条消息，就会将他取出
 *                      传递到Handler的handleMessage()方法中。每个线程中也只会有一个Looper对象
 *              ThreadLocal:线程本地存储区（TLS），每个线程都有自己私有的本地存储区域，不同线程之间彼此不能访问对方的TLS区域
 *                          ThreadLocal的作用是提供线程内的局部变量TLS，这种变量在线程的生命周期内起作用，每一个线程有他自己所属的值(线程隔离)
 *
 * 流程：
 *      首先，需要在主线程当中创建一个Handler对象，并重写handleMessage方法。
 *      然后，当子线程中需要进行UI操作时，就创建一个Message对象，并通过Handler将这条消息发送出去
 *      之后，这条消息会被添加到MessageQueue的队列中等待被处理，而Looper则会一直尝试从MessageQueue中取出待处理消息
 *      最后，分发回Handler的handleMessage()方法中
 *      由于Handler是在主线程中创建的，所以此时handleMessage()方法中的代码也会在主线程中运行，可安心进行UI操作
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView;
    private Handler handler1=new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);

        /*
            子线程向主线程发送消息
        */
        new Thread(() -> handler1.sendEmptyMessage(0)).start();
    }

    private static class MyHandler extends Handler{

        //弱引用持有MainActivity，GC回收时会被回收掉,防止Activity泄露
        private final WeakReference<MainActivity> mTarget;

        private MyHandler(MainActivity activity) {
            mTarget = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mTarget.get();//当要获得WeakReference引用的对象时，首先需要判断它是否已经被回收
            if (null!=activity){
                //业务逻辑
                if (msg.what==0){
                    Log.e(TAG, "change textview");
                    MainActivity ma = mTarget.get();
                    ma.textView.setText("hahahaha");
                }
                Toast.makeText(activity,"handleMessage",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
/*
    总结一下：
        Handler的子类对象一般是在主线程中进行创建，以便在两个线程中都能访问。
        在上面创建了Handler的子类MyHandler，并重写了handlerMessage方法
        然后创建了一个子线程，在子线程中我们使用MyHandler的对象调用sendEmptyMessage方法发送了一个空的Message
        之后就可以在主线程中接收到这个数据
*/










