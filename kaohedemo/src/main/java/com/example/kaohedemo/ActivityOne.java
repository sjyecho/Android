package com.example.kaohedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * 以标准模式启动
 */
public class ActivityOne extends Activity {

    private static final String TGA = "--SJY--";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityone);
        Log.d(TGA, "--默认模式启动onCreate--" + this.getLocalClassName());
        TextView view = findViewById(R.id.textviewone);
        Bundle data = getIntent().getExtras();
        String data1 = data.getString("Data1");
        view.setText(data1);
        findViewById(R.id.buttonone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityOne.this, ActivityOne.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TGA, "--默认模式启动onStart--" + this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TGA, "--默认模式启动onResume--" + this.getLocalClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TGA, "--默认模式启动onStop--" + this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TGA, "--默认模式启动onDestory--" + this.getLocalClassName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TGA, "--默认模式启动onRestart--" + this.getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TGA, "--默认模式启动onPause--" + this.getLocalClassName());
    }
}
