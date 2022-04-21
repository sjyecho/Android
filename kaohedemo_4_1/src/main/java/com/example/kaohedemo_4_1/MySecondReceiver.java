package com.example.kaohedemo_4_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MySecondReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //int new_id = intent.getIntExtra("new_id",1);
        Intent intentToContacts=new Intent(context,New_Contacts.class);
        context.startActivity(intentToContacts);
    }
}
