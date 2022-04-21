package com.example.kaohedemo5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    IntentFilter intentFilter;
    MyDynamicReceiver myDynamicReceiver;
    BindService.MyBinder binder;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("----------BindService已成功连接----------");
            binder = (BindService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("----------BindService已断开连接----------");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_service = findViewById(R.id.start_service);
        Button stop_service = findViewById(R.id.stop_service);
        Button start_intent_service = findViewById(R.id.start_intent_service);
        Button dynamic_broadcast = findViewById(R.id.dynamic_broadcast);
        Button static_broadcast = findViewById(R.id.static_broadcast);
        Button play_music = findViewById(R.id.play_music);
        Button pause_music = findViewById(R.id.pause_music);
        Button play_video = findViewById(R.id.play_video);
        Button bind = findViewById(R.id.bind);
        Button unbind = findViewById(R.id.unbind);
        Button getServiceStatus = findViewById(R.id.getServiceStatus);

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.bomb);

        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });

        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });

        start_intent_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("----------主线程为-----------"+Thread.currentThread().getId());
                Intent intent=new Intent(MainActivity.this,MyIntentService.class);
                startService(intent);
            }
        });

        static_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setPackage(getPackageName());
                intent.setAction("com.example.broadcastreceiver.static");
                intent.putExtra("msg","静态注册");
                sendBroadcast(intent);
            }
        });

        dynamic_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentFilter = new IntentFilter("com.example.broadcastreceiver.dynamic");
                registerReceiver(myDynamicReceiver, intentFilter);

                Intent intent = new Intent();
                intent.setPackage(getPackageName());
                intent.setAction("com.example.broadcastreceiver.dynamic");
                intent.putExtra("msg", "动态注册");
                sendBroadcast(intent);
            }
        });

        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        pause_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VideoPlayer.class);
                startActivity(intent);
            }
        });

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setPackage(getPackageName());
                intent.setAction("com.example.service.BIND_SERVICE");

                /*
                    参数1：Intent service
                            通过Intent指定要启动的Service
                    参数2：ServiceConnection conn
                            该参数是一个ServiceConnection对象，该对象用于监听访问者与Service之间的连接情况
                            当访问者与Service之间连接成功时将回调该ServiceConnection对象的onServiceConnected(ComponentName name, IBinder service)方法
                            当访问者与Service之间断开连接时将回调该ServiceConnection对象的onServiceDisconnected(ComponentName name)方法
                    参数3：int flags
                            指定绑定时是否自动创建Service(如果Service还未创建)。
                            该参数可指定为0(不自动创建)
                            或BIND_AUTO_CREATE(自动创建)
                */
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(conn);
            }
        });

        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Service的count值为：" + binder.getCount(), Toast.LENGTH_LONG).show();
            }
        });
    }
}