package com.example.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class BundleTest extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.name);
                EditText passwd = (EditText) findViewById(R.id.passwd);
                RadioButton male = (RadioButton) findViewById(R.id.male);
                String gender=male.isChecked()?"男":"女";
                Person p=new Person(name.getText().toString(),passwd.getText().toString(),gender);
                //创建一个Bundle对象
                Bundle data=new Bundle();
                data.putSerializable("person",p);
                //创建一个Intent
                Intent intent=new Intent(BundleTest.this,ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
}
