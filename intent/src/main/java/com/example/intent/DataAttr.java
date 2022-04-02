package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

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
                intent.setData(uri);
                startActivity(intent);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_EDIT);
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
