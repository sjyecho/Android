package com.example.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Button bn1 = (Button) findViewById(R.id.sbn1);
        Button bn2 = (Button) findViewById(R.id.sbn2);

        //返回上一个Activity,不关闭自己
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取启动当前Activity的上一个Intent
                Intent intent=new Intent(SecondActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });

        //返回上一个Activity,并关闭当前Activity
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取启动当前Activity的上一个Intent
                Intent intent=new Intent(SecondActivity.this,StartActivity.class);
                startActivity(intent);
                //结束当前Activity
                finish();
            }
        });
    }
}
