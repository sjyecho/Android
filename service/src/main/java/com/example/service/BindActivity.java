package com.example.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BindActivity extends Activity {

    Button bind, unbind, getServiceStatus;

    //保持所启动的Service的IBinder对象
    BindService.MyBinder binder;//此对象实现通信

    //定义一个ServiceConnection对象
    private final ServiceConnection conn = new ServiceConnection() {

        //当该Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("----------Service已成功连接----------");
            binder = (BindService.MyBinder) iBinder;
        }

        //当该Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("----------Service已断开连接----------");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindactivity);

        //获取程序界面中的start，stop，getServiceStatus按钮
        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        getServiceStatus = findViewById(R.id.getServiceStatus);

        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setAction("com.example.service.BIND_SERVICE");

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                    参数1：Intent service
                            通过Intent指定要启动的Service
                    参数2：ServiceConnection conn
                            该参数是一个ServiceConnection对象，该对象用于监听访问者与Service之间的连接情况
                            当访问者与Service之间连接成功时将回调该ServiceConnection对象的onServiceConnected(ComponentName name, IBinder service)方法
                            当访问者与Service之间断开连接时将回调该ServiceConnection对象的onServiceDisconnected(ComponentName name)方法
                    参数3：int flags
                            指定绑定时是否自动创建Service(如果Service还未创建)。
                            该参数可指定为0(不自动创建)
                            或BIND_AUTO_CREATE(自动创建)
                */
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    unbindService(conn);
                } catch (Exception e) {
                    Toast.makeText(BindActivity.this, "并未绑定Service", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(BindActivity.this, "Service的count值为：" + binder.getCount(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(BindActivity.this, "请先绑定Service", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
