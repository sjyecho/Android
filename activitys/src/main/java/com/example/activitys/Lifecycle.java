package com.example.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Lifecycle extends Activity {

    final String TAG="--SJY--";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"-----onStart-----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"-----onRestart-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"-----onResume-----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"-----onPause-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"-----onStop-----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"-----onDestory-----");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //输出日志
        Log.d(TAG,"-----onCreate-----");
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //结束该Activity
                Lifecycle.this.finish();
            }
        });

    }
}
