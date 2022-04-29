package com.example.aidl_client;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kaohedemo_4_1.IContacts;

public class AIDLClient extends AppCompatActivity {

    private IContacts contactsService;
    //private Button get;
    //EditText number, contact;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            contactsService = IContacts.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            contactsService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaohe_client);
        Button get = findViewById(R.id.getData);
        EditText number = findViewById(R.id.number);
        EditText contact = findViewById(R.id.contact);
        Intent intent = new Intent();
        intent.setAction("com.example.kaohedemo_4_1.ECHO.AIDL_SERVICE");
        intent.setPackage("com.example.kaohedemo_4_1");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    number.setText(contactsService.getNumber(1)+"");
                    contact.setText(contactsService.getPerson()+"");
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
