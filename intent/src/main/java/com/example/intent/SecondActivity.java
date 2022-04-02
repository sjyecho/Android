package com.example.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.Set;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        EditText show = (EditText) findViewById(R.id.show);
        /*ComponentName comp=getIntent().getComponent();
        show.setText("组件包名:"+comp.getPackageName()+"\n组件类名为:"+comp.getClassName());*/
        //获取该Activity对应的Intent的Action属性
        String action = getIntent().getAction();
        show.setText("Action属性为:"+action);

        EditText cate = (EditText) findViewById(R.id.cate);
        Set<String> cates = getIntent().getCategories();
        cate.setText("category的属性为:"+cates);
    }
}
