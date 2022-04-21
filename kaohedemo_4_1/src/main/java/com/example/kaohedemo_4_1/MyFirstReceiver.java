package com.example.kaohedemo_4_1;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyFirstReceiver extends BroadcastReceiver {

    ContentResolver contentResolver;

    //修改联系人id
    @Override
    public void onReceive(Context context, Intent intent) {
        contentResolver=context.getContentResolver();
        //拿到联系人ID
        //String id = intent.getStringExtra("id");
        Toast.makeText(context,"接收到的联系人id是："+intent.getStringExtra("id"),Toast.LENGTH_LONG).show();

        //ContentValues query_values=new ContentValues();
        //Cursor cursor=contentResolver.query(StaticUri.TableColumns.CONTACTS_QUERY,null,"_id like ?",new String[]{"%%"},null);
        //ArrayList<Map<String, String>> list = converCursorToList(cursor);


//        ContentValues update_values=new ContentValues();
//        //int new_id=contentResolver.update(StaticUri.TableColumns.CONTACTS_UPDATE,update_values,"id = ?",new String[]{id});
//        Intent sendToSecondBroadcast =new Intent();
//        sendToSecondBroadcast.setAction("android,intent.action.LOCALE_CHANGED");
//        sendToSecondBroadcast.setPackage(context.getPackageName());
//        sendToSecondBroadcast.putExtra("new_id",new_id);
//        context.sendBroadcast(sendToSecondBroadcast);
        Intent sendToSecondBroadcast =new Intent();
        sendToSecondBroadcast.setPackage(context.getPackageName());
        sendToSecondBroadcast.setAction("android,intent.action.LOCALE_CHANGED");
        context.sendBroadcast(sendToSecondBroadcast);

    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(StaticUri.TableColumns.CONTACTS_NAME, cursor.getString(1));
            map.put(StaticUri.TableColumns.CONTACTS_PHONE, cursor.getString(2));
            //map.put(StaticUri.TableColumns.BOOK_JG,cursor.getString(3));
            result.add(map);
        }
        return result;
    }
}
