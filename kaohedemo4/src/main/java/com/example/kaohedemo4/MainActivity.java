package com.example.kaohedemo4;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    BohaoFragment bohaoFragment = new BohaoFragment();//拨号
    LianxirenFragment lianxirenFragment = new LianxirenFragment();//联系人
    TonghuajiluFragment tonghuajiluFragment = new TonghuajiluFragment();//通话记录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, bohaoFragment).commit();

        Button button1=findViewById(R.id.button2);
        button1.setOnClickListener(view -> {
            //点击跳转拨号界面
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, bohaoFragment).commit();
        });

        Button button2=findViewById(R.id.button3);
        button2.setOnClickListener(view -> {
            //联系人界面
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, lianxirenFragment).commit();
        });

        Button button3=findViewById(R.id.button4);
        button3.setOnClickListener(view -> {
            //通话记录
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tonghuajiluFragment).commit();
        });
    }
}