package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class AidlService extends Service {

    private CatBinder catBinder;
    Timer timer = new Timer();
    String[] colors = new String[]{
            "红色",
            "黄色",
            "黑色"
    };
    double[] weights = new double[]{
            2.3,
            3.1,
            1.58
    };
    private String color;
    private double weight;

    public class CatBinder extends ICat.Stub {

        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catBinder = new CatBinder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 3);
                color = colors[rand];
                weight = weights[rand];
                System.out.println("----------" + rand);
            }
        }, 0, 800);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /*
            返回catBinder对象
            在绑定本地Service的情况下，该catBinder对象会直接传给客户端的ServiceConnection对象的onServiceConnected方法的第二个参数；
            在绑定远程Service的情况下，只将catBinder对象的代理传给客户端的ServiceConnection对象的onServiceConnected方法的第二个参数
        */
        return catBinder;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }
}
