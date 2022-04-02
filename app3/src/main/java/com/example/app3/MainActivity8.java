package com.example.app3;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

/**
 * 时钟的功能及用法
 */
public class MainActivity8 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8);
        //获取计时器组件
        final Chronometer ch = (Chronometer) findViewById(R.id.test8);
        //获取开始按钮
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置开始计时时间
                ch.setBase(SystemClock.elapsedRealtime());
                //启动计时器
                ch.start();
            }
        });
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //如果从开始计时到现在超过了20s
                //SystemClock.elapsedRealtime():返回自启动以来的毫秒数，包括睡眠时间
                if (SystemClock.elapsedRealtime()-ch.getBase()>20*1000){//getBase:返回setBase设置的时间
                    ch.stop();
                }
            }
        });
    }
}
