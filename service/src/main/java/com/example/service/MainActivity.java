package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 该Activity作为Service的访问者
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        //创建启动Service的Intent
        Intent intent = new Intent();
        //要为intent设置包名属性，否则无法启动
        intent.setPackage(this.getPackageName());
        //为Intent设置Action属性
        intent.setAction("com.example.service.FIRST_SERVICE");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("----------主线程为-----------"+Thread.currentThread().getId());
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });
        Button start_intent_service=findViewById(R.id.start_intentService);
        start_intent_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("----------主线程为-----------"+Thread.currentThread().getId());
                Intent intent=new Intent(MainActivity.this,MainIntentService.class);
                startService(intent);
            }
        });
    }
}