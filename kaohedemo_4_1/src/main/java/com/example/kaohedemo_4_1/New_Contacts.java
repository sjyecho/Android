package com.example.kaohedemo_4_1;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class New_Contacts extends AppCompatActivity {

    ContentResolver contentResolver;
    ContactsFragment contactsFragment=new ContactsFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contacts);
        getSupportFragmentManager().beginTransaction().replace(R.id.new_contacts_replace, contactsFragment).commit();
    }
}
