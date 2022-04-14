package com.example.kaohedemo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> number = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    RecyclerView recycler;
    RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.menu);

        View add = findViewById(R.id.menu_add);
        View deleteAll = findViewById(R.id.menu_deleteAll);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        toolbar.setTitle("联系人");
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));

        /*
          预添加数据
        */
        for (int i = 0; i < 30; i++) {
            name.add("李四 " + i);
            number.add("177" + i + (i + 1) + (i + 2) + i + "8776");
            time.add("2014-" + "0" + i + "-1" + i);
        }

        //适配器
        adapter = new RecyclerAdapter(this, name, number, time);
        //布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置布局
        recycler.setLayoutManager(manager);
        //设置动画
        recycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        recycler.setAdapter(adapter);

        /*
          为新增联系人按钮绑定单击事件
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setIcon(R.mipmap.search);
                builder.setTitle("添加联系人");
                //装载界面布局
                TableLayout loginForm = (TableLayout) getLayoutInflater().inflate(R.layout.add_layout, null);
                EditText usernameView = loginForm.findViewById(R.id.username);
                EditText numberEdittext = loginForm.findViewById(R.id.phoneNumber);
                //设置对话框显示的View对象
                builder.setView(loginForm);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username =usernameView.getText().toString();
                        String phoneNumber = String.valueOf(numberEdittext.getText());
                        name.add(username);
                        number.add(phoneNumber);
                        time.add("2022-04-14");
                        Log.d("sjy------",username+phoneNumber);
                        adapter.notifyDataSetChanged();
//                        RecyclerAdapter newItem = new RecyclerAdapter(MainActivity.this, name, number, time);
//                        recycler.setAdapter(newItem);
//                        recycler.notify();
                        Toast.makeText(MainActivity.this,"联系人添加成功",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //取消添加
                    }
                });
                builder.create().show();
            }
        });

        /*
            删除所有联系人事件
        */
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder2.setTitle("确定删除所有联系人么？");
                if (name.size()!=0){
                    builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (name.size()!=0){
                                recycler.removeAllViewsInLayout();
                                name.clear();
                                Toast.makeText(MainActivity.this,"已删除所有联系人",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this,"联系人为空",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"联系人为空",Toast.LENGTH_SHORT).show();
                }
                builder2.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder2.create().show();
            }
        });
    }
}