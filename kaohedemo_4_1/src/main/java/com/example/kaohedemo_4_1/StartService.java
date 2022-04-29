package com.example.kaohedemo_4_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StartService extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("com.example.kaohedemo_4_1.ECHO.AIDL_SERVICE");
        intent.setPackage("com.example.kaohedemo_4_1");
        startService(intent);
        finish();
    }
}
