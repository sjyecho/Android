package com.example.androidthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
 *
 * 流程：
 *      首先，需要在主线程当中创建一个Handler对象，并重写handleMessage方法。
 *      然后，当子线程中需要进行UI操作时，就创建一个Message对象，并通过Handler将这条消息发送出去
 *      之后，这条消息会被添加到MessageQueue的队列中等待被处理，而Looper则会一直尝试从MessageQueue中取出待处理消息
 *      最后，分发回Handler的handleMessage()方法中
 *      由于Handler是在主线程中创建的，所以此时handleMessage()方法中的代码也会在主线程中运行，可安心进行UI操作
 */
@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int UPDATE_TEXT = 1;

    TextView text;
    Button changeText;

    //在主线程中创建的
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    text.setText("nice to meet you!");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        changeText = findViewById(R.id.changeText);

        //此操作会报错:Only the original thread that created a view hierarchy can touch its views.
        //因为Android不允许在子线程中进行UI操作
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeText:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //text.setText("nice to meet you!");//Android不允许在子线程中进行UI操作
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}