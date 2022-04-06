package com.example.kaohedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ActivityFour extends Activity {

    private static final String TGA="--SJY--";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityfour);
        Log.d(TGA,"--singleInstance模式启动onCreate--"+this.getLocalClassName());
        findViewById(R.id.buttonfour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityFour.this,ActivityFour.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TGA,"--singleInstance模式启动onStart--"+this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TGA,"--singleInstance模式启动onResume--"+this.getLocalClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TGA,"--singleInstance模式启动onStop--"+this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TGA,"--singleInstance模式启动onDestory--"+this.getLocalClassName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TGA,"--singleInstance模式启动onRestart--"+this.getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TGA,"--singleInstance模式启动onPause--"+this.getLocalClassName());
    }


}
