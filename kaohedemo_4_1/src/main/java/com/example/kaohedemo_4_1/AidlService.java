package com.example.kaohedemo_4_1;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AidlService extends Service {

    private UserBinder userBinder;
    ContentResolver contentResolver;
    Cursor cursor = null;
    private String name;
    private String phone;

    public class UserBinder extends IContacts.Stub {
        @Override
        public int getNumber(int num) throws RemoteException {
            return num + 1;
        }

        @Override
        public String getPerson() throws RemoteException {
            return name + "--" + phone;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        userBinder = new UserBinder();
        contentResolver = getContentResolver();
        cursor = this.contentResolver.query(StaticUri.TableColumns.CONTACTS_QUERY, null, "name like ? or phone like ?", new String[]{"%%", "%%"}, null);
        ArrayList<Map<String, String>> list = converCursorToList(cursor);
        for (Map<String, String> map : list) {
            name = map.get("name");
            phone = map.get("phone");
            return;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return userBinder;
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
