package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * 显示在标题上的进度条
 * 直接在窗口标题上显示,这种进度条甚至不需要使用ProgressBar组件,它是直接由Activity的方法启动的。
 * 需要进过如下两步：
 * 1.调用Activity的requestWindowFeature()方法，该方法根据传入的参数可启动特定的窗口特征
 * 例如传入Window.FEATURE_INDETERMINATE_PROGRESS在窗口标题上显示不带进度的进度条
 * 传入Window.FEATURE_PROGRESS则显示带进度的进度条
 * 2.调用Activity的setProgressBarVisibility(boolean)或setProgressBarIndeterminateVisibility(boolean)方法即可控制进度条的显示和隐藏
 */
public class DemoProgressBar02 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗口特征：启用显示进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        //设置窗口特征：启用不显示进度的进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.progressbar02);
        Button bn1 = findViewById(R.id.bn01);
        Button bn2 = findViewById(R.id.bn02);

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示不带进度的进度条
                setProgressBarIndeterminateVisibility(true);
                //显示带进度的进度条
                setProgressBarVisibility(true);
                //设置进度条的进度
                setProgress(4500);
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示不带进度的进度条
                setProgressBarIndeterminateVisibility(false);
                //显示带进度的进度条
                setProgressBarVisibility(false);
            }
        });
    }
}