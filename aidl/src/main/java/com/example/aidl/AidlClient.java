package com.example.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AidlClient extends AppCompatActivity {

    private ICat catService;
    private Button get;
    EditText color, weight;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //获取远程Service的onBind方法返回的对象的代理
            catService = ICat.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            catService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
        get = findViewById(R.id.get);
        color = findViewById(R.id.color);
        weight = findViewById(R.id.weight);
        //创建所需绑定服务的Intent
        Intent intent = new Intent();
        intent.setAction("com.example.aidl.action.AIDL_SERVICE");
        //需要添加包名
        intent.setPackage(getPackageName());
        //绑定远程服务
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //获取并显示远程Service的状态
                    color.setText(catService.getColor());
                    weight.setText(catService.getWeight() + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(conn);
    }
}
