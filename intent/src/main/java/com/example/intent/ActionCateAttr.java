package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * 使用intent-filter标签启动Activity
 */
public class ActionCateAttr extends Activity {

    //定义一个Action常量
    final static String CRAZYIT_ACTION="action";
    //定义一个Category常量
    final static String CRAZY_CATEGORY="category";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                //设置Action属性
                intent.setAction(ActionCateAttr.CRAZYIT_ACTION);
                //添加Category属性
                intent.addCategory(ActionCateAttr.CRAZY_CATEGORY);
                startActivity(intent);
            }
        });
    }
}
