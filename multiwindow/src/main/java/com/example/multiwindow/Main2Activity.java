package com.example.multiwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Main2Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ECHO","22222--onCreate");
        setContentView(R.layout.activity_2_main);
        findViewById(R.id.start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode){
            Log.d("ECHO","22222--进入多窗口模式");
            Toast.makeText(this,"22222进入多窗口模式",Toast.LENGTH_LONG).show();
        }else {
            Log.d("ECHO","22222--退出多窗口模式");
            Toast.makeText(this,"22222退出多窗口模式",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ECHO","22222--onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ECHO","22222--onDestroy");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ECHO","22222--onStop");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ECHO","22222--onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ECHO","22222--onRestart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ECHO","22222--onPause");

    }
}
