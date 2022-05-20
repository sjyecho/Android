package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
    BroadcastReceiver的两种注册方式：
        （1）动态注册 使用IntentFilter
                动态注册方式的BroadcastReceiver，生命周期仅限于当前注册的activity，
                离开activity一定要解除注册，否则就会抛出错误，但是这个错误不会导致app崩溃
        （2）静态注册在AndroidManifest.xml文件中进行配置
                静态注册的BroadcastReceiver，生命周期不仅局限于activity，
                activity关闭与否，不受影响，即使app退出了还是会收到广播
*/
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context,
                "接收到的Intent的Action为："+intent.getAction()+"\n消息内容是："+intent.getStringExtra("msg"),
                Toast.LENGTH_LONG
        ).show();
    }
}
