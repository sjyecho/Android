package com.example.contentprovider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MonitorSms extends Activity {

    private final static int EXTERNAL_STORAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, new SmsObserver(new Handler()));
    }

    private final class SmsObserver extends ContentObserver {

        public SmsObserver(Handler handler) {
            super(handler);

        }

        @SuppressLint("Range")
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Cursor cursor=getContentResolver().query(Uri.parse("content://sms/outbox"),null,null,null,null);
            while (cursor.moveToNext()){
                StringBuilder sb=new StringBuilder();
                //获取短信的发送地址
                sb.append("address=").append(cursor.getString(cursor.getColumnIndex("address")));
                //获取短信的标题
                sb.append(";subject=").append(cursor.getString(cursor.getColumnIndex("subject")));
                //获取短信的内容
                sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));
                //获取短信的发送时间
                sb.append(";time=").append(cursor.getString(cursor.getColumnIndex("date")));
                System.out.println("Has Sent SMS:::"+sb.toString());
            }
        }
    }
}
