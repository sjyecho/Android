package com.example.control_listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

/**
 * 登录对话框
 * 自定义的AlertDialog，使用界面布局文件定义
 */
public class LoginDialog extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdialog_demo);
        Button bn = findViewById(R.id.bn);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setIcon(R.drawable.toux1);
                builder.setTitle("自定义普通对话框");
                //装载界面布局
                TableLayout loginForm = (TableLayout) getLayoutInflater().inflate(R.layout.login_demo, null);
                //设置对话框显示的View对象
                builder.setView(loginForm);
                //为对话框设置一个确定按钮
                builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //此处可进行登录操作
                    }
                });
                //为对话框设置一个取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //取消登录，不做任何事情
                    }
                });
                //创建并显示对话框
                builder.create().show();
            }
        });
    }
}
