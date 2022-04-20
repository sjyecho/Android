package com.example.kaohedemo_4_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

//    DialFragment dialFragment=new DialFragment();//拨号
//    ContactsFragment contactsFragment=new ContactsFragment();//联系人
//    CollectionContactsFragment collectionContactsFragment=new CollectionContactsFragment();//收藏
//    CallRecordFragment callRecordFragment=new CallRecordFragment();//通话记录

    FragmentTabHost mTabHost;
    LayoutInflater layoutInflater;
    Class[] fragmentArray ={DialFragment.class,ContactsFragment.class,CollectionContactsFragment.class,CallRecordFragment.class};
    String[] mTextArray={"拨号","联系人","收藏","通话记录"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        layoutInflater=LayoutInflater.from(this);
        //实例化TabHost对象
        mTabHost=(FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.container_fragment);

        int count=fragmentArray.length;

        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ic_launcher_foreground);
        }
    }

    public View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view,null);
        TextView textView=view.findViewById(R.id.tab_text);
        textView.setText(mTextArray[index]);
        return view;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        setContentView(R.layout.activity_main);
////        Button sjcx = findViewById(R.id.sjcx);
////        Button lbcx = findViewById(R.id.lbcx);
////        sjcx.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(MainActivity.this, DemoResolver.class);
////                startActivity(intent);
////            }
////        });
////        lbcx.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(MainActivity.this, DemoResolverType.class);
////                startActivity(intent);
////            }
////        });
//    }
}