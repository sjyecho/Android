package com.example.firstexam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView view;
    private static final String TGA="--sjy--";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TGA,"--onCreate--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TGA,"--onResume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TGA,"--onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TGA,"--onStop--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TGA,"--onRestart--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TGA,"--onDestory--");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TGA,"--onCreate--");
        Button button1 = findViewById(R.id.button1);
        view=findViewById(R.id.view1);
         button1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                 startActivityForResult(intent,0);
             }
         });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode==0&&resultCode==0){
            //从intent中取出数据
            Bundle data = intent.getExtras();
            String data1 = data.getString("data");
            view.setText(data1);
        }
    }
}