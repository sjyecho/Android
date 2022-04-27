package com.example.multiwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3_main);
        Button check=findViewById(R.id.check);
        check.setOnClickListener(view -> {
            if (isInMultiWindowMode()){
                Toast.makeText(Main3Activity.this,"支持多窗口模式",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Main3Activity.this,"不支持多窗口模式",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
