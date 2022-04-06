package com.example.kaohedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TGA="--SJY--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TGA,"--onCreate--"+this.getLocalClassName());
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityOne.class);
                Bundle data=new Bundle();
                data.putString("Data1","传递参数");
                intent.putExtras(data);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityTwo.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityThree.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityFour.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TGA,"--onStart--"+this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TGA,"--onResume--"+this.getLocalClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TGA,"--onStop--"+this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TGA,"--onDestory--"+this.getLocalClassName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TGA,"--onRestart--"+this.getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TGA,"--onPause--"+this.getLocalClassName());
    }
}