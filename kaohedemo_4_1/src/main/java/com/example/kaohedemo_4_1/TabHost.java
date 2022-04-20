package com.example.kaohedemo_4_1;

import android.app.TabActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;


public class TabHost extends TabActivity {

    public View dialTabHost;
    View contactsTabHost;
    View callRecordTabHost;
    View collectionContactsTabHost;
    //private FragmentManager fragmentManager;
    DialFragment dialFragment=new DialFragment();//拨号
    ContactsFragment contactsFragment=new ContactsFragment();//联系人
    CollectionContactsFragment collectionContactsFragment=new CollectionContactsFragment();//收藏
    CallRecordFragment callRecordFragment=new CallRecordFragment();//通话记录

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.widget.TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.tabhost,tabHost.getTabContentView(),true);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("拨号").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("联系人列表").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("通话记录").setContent(R.id.tab03));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("收藏").setContent(R.id.tab04));


        dialTabHost =findViewById(R.id.tab01);
        contactsTabHost =findViewById(R.id.tab02);
        callRecordTabHost =findViewById(R.id.tab03);
        collectionContactsTabHost =findViewById(R.id.tab04);
    }

//    @Override
//    public FragmentManager getFragmentManager() {
//        return fragmentManager;
//    }
//
//    public void setFragmentManager(FragmentManager fragmentManager) {
//        this.fragmentManager = fragmentManager;
//    }
}
