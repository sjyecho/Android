package com.example.multiwindow;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 *
 * 给Activity加上如下配置可以保证切换成多屏或者画中画模式时Activity不会销毁重建
 *      android:configChanges="screenSize|smallestScreenSize|screenLayout|orientation"
 *
 * 多窗口变更通知和查询
 *      1.Activity.isInMultiWindowMode()
 *          调用该方法以确认Activity是否处于多窗口模式
 *      2.Activity.isInPictureInPictureMode()
 *          调用该方法以确认Activity是否处于画中画模式
 *      3.Activity.onMultiWindowModeChanged()
 *          Activity进入或退出多窗口模式时系统将调用此方法。在Activity进入多窗口模式时传递true，退出时传递false
 *      4.Activity.onPictureInPictureModeChanged()
 *          Activity进入或退出画中画模式时系统将调用此方法。在Activity进入画中画模式时传递true，退出时传递false
 * 注：每个方法都有Fragment版本
 */
public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ECHO","11111--onCreate");
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_activity).setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    /**
     *
     * 若配置了此条属性
     *      android:configChanges="screenSize|smallestScreenSize|screenLayout|orientation"
     * 不管是进入多窗口模式，还是横竖屏切换，活动都不会被重新创建
     * 而是会将屏幕发生变化的事件通知到Activity的onConfigurationChanged()方法中
     * 因此，若想在屏幕发生变化时进行相应的逻辑处理，那么在活动中重写此方法即可
     * @param newConfig 1
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode){
            Log.d("ECHO","11111--进入多窗口模式");
            Toast.makeText(this,"11111进入多窗口模式",Toast.LENGTH_SHORT).show();
        }else {
            Log.d("ECHO","11111--退出多窗口模式");
            Toast.makeText(this,"11111退出多窗口模式",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ECHO","11111--onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ECHO","11111--onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ECHO","11111--onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ECHO","11111--onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ECHO","11111--onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ECHO","11111--onPause");
    }
}