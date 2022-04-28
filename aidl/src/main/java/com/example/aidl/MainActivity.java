package com.example.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("com.example.aidl.action.AIDL_SERVICE");
        intent.setPackage(getPackageName());
        startService(intent);
        finish();
    }
}