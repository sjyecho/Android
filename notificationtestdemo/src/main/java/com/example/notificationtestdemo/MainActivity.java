package com.example.notificationtestdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final int NOTIFICATION_ID_ONE = 0x1111;
    static final int NOTIFICATION_ID_TWO = 0x2222;
    static final int NOTIFICATION_ID_THREE = 0x3333;
    static final int NOTIFICATION_ID_FOUR = 0x4444;
    static final int NOTIFICATION_ID_FIVE = 0x5555;

    String id_one = "channel_1";//channel的id
    String id_two = "channel_2";//channel的id
    String id_three = "channel_3";//channel的id
    String id_four = "channel_4";//channel的id
    String id_five = "channel_5";//channel的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_one=findViewById(R.id.bn1);
        Button button_two=findViewById(R.id.bn2);
        Button button_three=findViewById(R.id.bn3);
        Button button_four=findViewById(R.id.bn4);
        Button button_five=findViewById(R.id.bn5);

        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel_1 = new NotificationChannel(
                        "channel_1",
                        "one",
                        NotificationManager.IMPORTANCE_LOW);
                Notification notify=new NotificationCompat.Builder(MainActivity.this,id_one)
                        .setContentTitle("标题1")
                        .setContentText("正文内容")
                        .setSmallIcon(R.mipmap.search)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel_1);
                notificationManager.notify(NOTIFICATION_ID_ONE,notify);
            }
        });

        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel_2 = new NotificationChannel(
                        "channel_2",
                        "two",
                        NotificationManager.IMPORTANCE_HIGH);
                Notification notify=new NotificationCompat.Builder(MainActivity.this,id_two)
                        .setContentTitle("标题2")
                        .setContentText("正文内容")
                        .setSmallIcon(R.mipmap.search)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel_2);
                notificationManager.notify(NOTIFICATION_ID_TWO,notify);
            }
        });

        button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel_3 = new NotificationChannel(
                        "channel_3",
                        "three",
                        NotificationManager.IMPORTANCE_HIGH);
                channel_3.enableVibration(true);
                Notification notify=new NotificationCompat.Builder(MainActivity.this,id_three)
                        .setContentTitle("标题3")
                        .setContentText("正文内容")
                        .setSmallIcon(R.mipmap.search)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel_3);
                notificationManager.notify(NOTIFICATION_ID_THREE,notify);
            }
        });

        button_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel_4 = new NotificationChannel(
                        "channel_4",
                        "four",
                        NotificationManager.IMPORTANCE_HIGH);
                channel_4.enableLights(true);
                Notification notify=new NotificationCompat.Builder(MainActivity.this,id_four)
                        .setContentTitle("标题4")
                        .setContentText("正文内容")
                        .setSmallIcon(R.mipmap.search)
                        .build();
                notify.flags=Notification.FLAG_SHOW_LIGHTS;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel_4);
                notificationManager.notify(NOTIFICATION_ID_FOUR,notify);
            }
        });

        button_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel_5 = new NotificationChannel(
                        "channel_5",
                        "five",
                        NotificationManager.IMPORTANCE_HIGH);
                channel_5.enableVibration(true);
                Notification notify=new NotificationCompat.Builder(MainActivity.this,id_five)
                        .setContentTitle("标题5")
                        .setContentText("正文内容")
                        .setSmallIcon(R.mipmap.search)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(channel_5);
                notificationManager.notify(NOTIFICATION_ID_FIVE,notify);
            }
        });
    }
}