package com.example.kaohedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ActivityThree extends Activity {

    private static final String TGA="--SJY--";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitythree);
        Log.d(TGA,"--栈内复用模式启动onCreate--"+this.getLocalClassName());
        findViewById(R.id.buttonthree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityThree.this,ActivityThree.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TGA,"--栈内复用模式启动onStart--"+this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TGA,"--栈内复用模式启动onResume--"+this.getLocalClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TGA,"--栈内复用模式启动onStop--"+this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TGA,"--栈内复用模式启动onDestory--"+this.getLocalClassName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TGA,"--栈内复用模式启动onRestart--"+this.getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TGA,"--栈内复用模式启动onPause--"+this.getLocalClassName());
    }
}
