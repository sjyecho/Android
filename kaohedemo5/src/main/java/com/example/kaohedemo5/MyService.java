package com.example.kaohedemo5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 *
 * 通过startService()启动Service的生命周期
 * onCreate() -> onStart() -> (Service运行中) -> (服务被通知停止) -> onDestroy()
 *
 */
public class MyService extends Service {

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


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("-----------MyService is Created------------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("-----------MyService is Started------------");
        System.out.println("----------Service当前线程为----------"+Thread.currentThread().getId());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("-----------MyService is Destoryed,销毁线程为------------"+Thread.currentThread().getId());

    }
}
