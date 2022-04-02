package com.example.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView name = (TextView) findViewById(R.id.name);
        TextView passwd = (TextView) findViewById(R.id.passwd);
        TextView gender = (TextView) findViewById(R.id.gender);
        //获取启动该Result的Intent
        Intent intent=getIntent();
        //获取该intent所携带的数据
        Bundle data = intent.getExtras();
        //从Bundle数据包中取出数据
        Person p=(Person)data.getSerializable("person");
        name.setText("您的用户名为:"+p.getName());
        passwd.setText("您的密码为:"+p.getPasswd());
        gender.setText("您的性别为:"+p.getGender());
    }
}
