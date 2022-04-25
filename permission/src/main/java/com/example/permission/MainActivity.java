package com.example.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
 *
 * 运行时权限：
 *
 * ------------------------------------------------------------------------------------
 *       权限组名                                    权限名
 * ------------------------------------------------------------------------------------
 *       CALENDAR                                  READ_CALENDAR
 *                                                 WRITE_CALENDAR
 * ------------------------------------------------------------------------------------
 *       CAMERA                                      CAMERA
 * ------------------------------------------------------------------------------------
 *       CONTACTS                                  READ_CONTACTS
 *                                                 WRITE_CONTACTS
 *                                                 GET_ACCOUNTS
 * ------------------------------------------------------------------------------------
 *       LOCATION                                  ACCESS_FINE_LOCATION
 *                                                 ACCESS_COARSE_LOCATION
 * ------------------------------------------------------------------------------------
 *       MICROPHONE                                RECORD_AUDIO
 * ------------------------------------------------------------------------------------
 *       PHONE                                     READ_PHONE_STATE
 *                                                 CALL_PHONE
 *                                                 READ_CALL_LOG
 *                                                 WRITE_CALL_LOG
 *                                                 ADD_VOICEMAIL
 *                                                 USE_SIP
 *                                                 PROCESS_OUTGOING_CALLS
 * ------------------------------------------------------------------------------------
 *       SENSORS                                   BODY_SENSORS
 * ------------------------------------------------------------------------------------
 *       SMS                                       SEND_SMS
 *                                                 RECEIVE_SMS
 *                                                 READ_SMS
 *                                                 RECEIVE_WAP_PUSH
 *                                                 RECEIVE_MMS
 * ------------------------------------------------------------------------------------
 *       STORAGE                                   READ_EXTERNAL_STORAGE
 *                                                 WRITE_EXTERNAL_STORAGE
 *
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "您未进行授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeCall = findViewById(R.id.make_call);
        makeCall.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                call();
            }
        });
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }
}