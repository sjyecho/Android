package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MessageFragment messageFragment = new MessageFragment();
    DongtaiFragment dongtaiFragment = new DongtaiFragment();
    ContactFragment contactFragment = new ContactFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button1=findViewById(R.id.button2);
        button1.setOnClickListener(view -> {
            //加载messageFragment,将之前的布局控件换成Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,messageFragment).commit();
        });

        Button button2=findViewById(R.id.button3);
        button2.setOnClickListener(view -> {
            //加载contactFragment,将之前的布局控件换成Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,contactFragment).commit();
        });

        Button button3=findViewById(R.id.button4);
        button3.setOnClickListener(view -> {
            //加载dongtaiFragment,将之前的布局控件换成Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,dongtaiFragment).commit();
        });
    }
}