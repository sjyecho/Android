package com.example.echo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> number=new ArrayList<>();
    ArrayList<String> time=new ArrayList<>();
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler=findViewById(R.id.recycler);
        Toolbar toolbar=findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.menu);
        View add = findViewById(R.id.menu_add);
        toolbar.setTitle("联系人界面");
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        for (int i = 0; i < 20; i++) {
            name.add("李四--"+i);
            number.add("17787642345");
            time.add("22-04-15");
        }
        RecyclerAdapter adapter=new RecyclerAdapter(MainActivity.this,name,number,time);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);

        //删除所有
        View delete = findViewById(R.id.menu_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycler.removeAllViews();
                recycler.removeAllViewsInLayout();
                Toast.makeText(MainActivity.this,"已删除所有联系人条目",Toast.LENGTH_SHORT).show();
            }
        });
        //添加一个联系人
        builder=new AlertDialog.Builder(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("添加联系人");
                TableLayout loginView=(TableLayout) getLayoutInflater().inflate(R.layout.add_layout,null);
                builder.setView(loginView);
                EditText userName=findViewById(R.id.username);
                EditText phoneNumber=findViewById(R.id.phoneNumber);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = userName.getText().toString();
                        String phonenumber = phoneNumber.getText().toString();
                        name.add(username);
                        number.add(phonenumber);
                        time.add("22-04-15");
                        RecyclerAdapter newItem=new RecyclerAdapter(MainActivity.this,name,number,time);
                        recycler.setAdapter(newItem);
                        Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }
}