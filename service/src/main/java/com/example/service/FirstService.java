package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class FirstService extends Service {

    /**
     * 该方法是Service子类必须实现的方法。该方法返回一个IBinder对象
     * 应用程序可通过该对象与Service组件通信
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Service被创建时回调该方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("-----------Service is Created------------");
    }

    /**
     * Service被启动时回调该方法
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("-----------Service is Started------------");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Service被关闭之前回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("-----------Service is Destoryed------------");

    }
}
