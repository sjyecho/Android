package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Notification的功能和用法
 */
public class MainActivity extends AppCompatActivity {

    static final int NOTIFICATION_ID = 0x1123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取应用界面中的Button对象
        Button send = findViewById(R.id.send);
        Button del = findViewById(R.id.del);

        String id = "channel_1";//channel的id
        int importance = NotificationManager.IMPORTANCE_LOW;//channel的重要性

        //NotificationChannel是Android 8.0之后为通知新增的频道管理
        NotificationChannel channel = new NotificationChannel(//生成channel
                "channel_1",
                "123",
                NotificationManager.IMPORTANCE_HIGH);//重要等级为HIGH时会弹出悬浮

        /*
            getId() 获取 ChannleId
            enableLights() 开启指示灯，如果设备有的话。
            setLightColor() 设置指示灯颜色
            enableVibration() 开启震动
            setVibrationPattern() 设置震动频率
            setImportance() 设置频道重要性
            getImportance() 获取频道重要性
            setSound() 设置声音
            getSound() 获取声音
            setGroup() 设置 ChannleGroup
            getGroup() 得到 ChannleGroup
            setBypassDnd() 设置绕过免打扰模式
            canBypassDnd() 检测是否绕过免打扰模式
            getName() 获取名称
            setLockScreenVisibility() 设置是否应在锁定屏幕上显示此频道的通知
            getLockscreenVisibility()检测是否应在锁定屏幕上显示此频道的通知
            setShowBadge() 设置是否显示角标
            canShowBadge()检测是否显示角标
            setImportance 重要程度
        */
        //为channel添加属性
        channel.enableVibration(true); //震动
        channel.enableLights(true);//提示灯
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);//在安全锁屏上隐藏

        //获取系统的NotificationManager服务
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        Intent intent = new Intent(MainActivity.this, OtherActivity.class);
        /*
            PendingIntent用于描述Intent及其最终的行为
            你可以通过 getActivity(Context context, int requestCode, Intent intent, int flags) 系列方法从系统取得一个 用于启动一个Activity的PendingIntent对象 ,
            可以通过 getService(Context context, int requestCode, Intent intent, int flags) 方法从系统取得一个 用于启动一个Service的PendingIntent对象
            可以通过 getBroadcast(Context context, int requestCode, Intent intent, int flags) 方法从系统取 得一个用于向BroadcastReceiver的Intent广播的
        */
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        //创建一个Notification
        Notification notify = new NotificationCompat.Builder(MainActivity.this, id)
                .setContentTitle("标题")
                .setContentText("启动其他Activity的通知")
                .setSubText("subtext的内容")
                .setSmallIcon(R.mipmap.search)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.user))
                .setContentIntent(pi)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_HIGH)//设置优先级
                .setOngoing(true)//true:通知无法清楚；false：通知可以被清楚
                .setAutoCancel(true)//点击通知后，通知栏是否自动删除通知
                .setTicker("111111111")
                .build();
        //notify.defaults = Notification.DEFAULT_ALL;

        send.setOnClickListener(view -> {
            //发送通知
            notificationManager.notify(NOTIFICATION_ID, notify);
        });

        del.setOnClickListener(v -> {
            notificationManager.cancel(NOTIFICATION_ID);
        });
    }
}