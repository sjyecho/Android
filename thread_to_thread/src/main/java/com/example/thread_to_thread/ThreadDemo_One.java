package com.example.thread_to_thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("all")
public class ThreadDemo_One extends Activity implements View.OnClickListener {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Object obj1 = msg.obj;
                    String data = obj1.toString();
                    editText_two.setText(data);
                    /*
                        此操作也是在主线程中
                    */
                    Log.d("ECHO", "处理send方法的线程：" + Thread.currentThread().getId());
                    break;
                case 2:
                    editText_five.setText("hello,python");
                    break;
                case 3:
                    Object obj2 = msg.obj;
                    String data2 = obj2.toString();
                    editText_six.setText(data2);
                    Log.d("ECHO", "处理sendMessageAtTime方法的线程：" + Thread.currentThread().getId());
                    Log.d("ECHO", "sendMessageAtTime方法执行完毕：" + System.currentTimeMillis());

                    break;
                case 4:
                    Object obj3=msg.obj;
                    String data3 = obj3.toString();
                    editText_seven.setText(data3);
                    break;

            }
        }
    };
    EditText editText_one, editText_two, editText_three, editText_four, editText_five, editText_six,editText_seven;
    Button post_change, send_change, postdelay_change,sendEmpty_change,send_delay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_demo_one);

        editText_one = findViewById(R.id.textview_one);
        post_change = findViewById(R.id.post_change_text);

        editText_two = findViewById(R.id.textview_two);
        send_change = findViewById(R.id.send_change_text);

        editText_three = findViewById(R.id.textview_three);
        postdelay_change = findViewById(R.id.post_delay_change_text);

        editText_four = findViewById(R.id.textview_four);

        editText_five=findViewById(R.id.textview_five);
        sendEmpty_change=findViewById(R.id.send_empty_change_text);

        editText_six=findViewById(R.id.textview_six);

        editText_seven=findViewById(R.id.textview_seven);
        send_delay=findViewById(R.id.send_delay);

        send_delay.setOnClickListener(this);
        send_change.setOnClickListener(this);
        post_change.setOnClickListener(this);
        postdelay_change.setOnClickListener(this);
        sendEmpty_change.setOnClickListener(this);

        Log.d("ECHO", "主线程：" + Thread.currentThread().getId());

        long now = System.currentTimeMillis();//当前时间
        System.out.println("当前时间:" + now);
        long boot = SystemClock.elapsedRealtime();//开机时间
        System.out.println("开机时间:" + boot);
        System.out.println("唤醒时间：" + SystemClock.uptimeMillis());//唤醒时间
        long temp = now - boot;
        int time = (int) temp;
        System.out.println("time:" + time);

        /*
            postAtTime方法是以Android的系统唤醒时间为基准的
            开机时间：SystemClock.elapsedRealtime
            唤醒时间：SystemClock.uptimeMillis
        */
        Log.d("ECHO", "postAtTime方法开始执行：" + System.currentTimeMillis());
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                editText_four.setText("hello,C");
                Log.d("ECHO", "postAtTime方法执行完毕：" + System.currentTimeMillis());
            }
        }, boot + 5000);

        Message send_delay_message = Message.obtain();
        send_delay_message.what=3;
        send_delay_message.obj="hello kotlin";
        Log.d("ECHO", "sendMessageAtTime方法开始执行：" + System.currentTimeMillis());
        handler.sendMessageAtTime(send_delay_message,boot + 5000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                            使用post方法直接更新UI，不经过handleMessage处理
                            通过Log可以发现，post方法的线程就是主线程，所以可以直接更新UI
                        */
                        Log.d("ECHO", "post方法所处的线程：" + Thread.currentThread().getId());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                editText_one.setText("hello,android");
                                Log.d("ECHO", "post方法执行的线程：" + Thread.currentThread().getId());

                            }
                        });
                    }
                }).start();
                break;
            case R.id.send_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = Message.obtain();
                        message.what = 1;
                        message.obj="hello java";
                        /*
                             sendMessage可以携带数据
                        */
                        handler.sendMessage(message);
                        Log.d("ECHO", "send方法的线程：" + Thread.currentThread().getId());

                    }
                }).start();
                break;
            case R.id.post_delay_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ECHO", "postDelayed方法所处的线程：" + Thread.currentThread().getId());
                        Log.d("ECHO", "postDelayed方法开始执行：" + System.currentTimeMillis());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                editText_three.setText("hello,c++");
                                Log.d("ECHO", "postDelayed方法结束执行：" + System.currentTimeMillis());
                                Log.d("ECHO", "postDelayed方法执行的线程：" + Thread.currentThread().getId());
                            }
                        }, 2000);
                    }
                }).start();
                break;
            case R.id.send_empty_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //sendEmptyMessage不能携带数据
                        handler.sendEmptyMessage(2);
                    }
                }).start();
                break;
            case R.id.send_delay:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = Message.obtain();
                        message.what=4;
                        message.obj="hello golang";
                        handler.sendMessageDelayed(message,5000);
                    }
                }).start();
                break;
        }
    }
}
