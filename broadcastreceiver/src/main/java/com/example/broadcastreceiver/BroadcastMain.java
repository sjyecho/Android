package com.example.broadcastreceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadcastMain extends AppCompatActivity {

    IntentFilter intentFilter;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter("com.example.broadcastreceiver.BROADCASTRECEIVER");
        myReceiver = new MyReceiver();

        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent();
//                //加包名才可以
//                intent.setPackage(getPackageName());
//                intent.setAction("com.example.broadcastreceiver.BROADCASTRECEIVER");
//                intent.putExtra("msg","静态注册");
//                sendBroadcast(intent);

                //这里仅注册成功，并不启动Broadcast
                registerReceiver(myReceiver, intentFilter);

                Intent intent = new Intent();
                intent.setPackage(getPackageName());
                intent.setAction("com.example.broadcastreceiver.BROADCASTRECEIVER");
                intent.putExtra("msg", "动态注册");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销动态注册的BroadcastReceiver
        unregisterReceiver(myReceiver);
    }
}