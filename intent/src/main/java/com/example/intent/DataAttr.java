package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * 使用三个按钮，分别跳转网页、联系人、拨号界面
 */
public class DataAttr extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataattr);
        Button bn1 = (Button) findViewById(R.id.bn1);
        Button bn2 = (Button) findViewById(R.id.bn2);
        Button bn3 = (Button) findViewById(R.id.bn3);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                String data="http://www.baidu.com";
                //根据指定字符串解析出Uri对象
                Uri uri= Uri.parse(data);//parse用于解析URI编码的字符串
                intent.setAction(Intent.ACTION_VIEW);
                //设置Data属性
                /*
                    Data属性通常用于向Action属性提供操作的数据。Data属性接受一个Uri对象。
                    Type属性则用于明确指定Data属性所指定数据的类型。
                        通常来说，当Intent不指定Data属性时Type属性才会起作用，否则Android系统会根据Data属性值来分析数据的类型，
                        因此无须指定Type属性
                    一旦Intent同时指定了Action、Data属性，那么Android将根据指定的数据类型来启动特定的应用程序，并对指定程序执行相应的操作
                */
                intent.setData(uri);
                startActivity(intent);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_EDIT);
                /*
                    content://com.android.contacts/contacts/1
                    content:前缀：该前缀表明该数据类型为联系人信息
                    //com.android.contacts/contacts/1：该数据表明操作_id为1的联系人数据

                */
                String data="content://com.android.contacts/contacts/1";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                //String data="content://com.android.contacts/contacts/1";
                String data="tel:123";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
