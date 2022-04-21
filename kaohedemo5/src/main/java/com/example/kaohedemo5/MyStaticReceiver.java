package com.example.kaohedemo5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyStaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收到的消息内容是："+intent.getStringExtra("msg"),Toast.LENGTH_LONG).show();
    }
}
