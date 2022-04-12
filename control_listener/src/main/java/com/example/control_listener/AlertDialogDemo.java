package com.example.control_listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AlertDialogDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_demo);
        Button bn = findViewById(R.id.bn01);
        TextView show = findViewById(R.id.show);
        //定义一个AlertDialog.Builder对象
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置对话框的图标
                builder.setIcon(R.drawable.toux1);
                //设置对话框的标题
                builder.setTitle("自定义普通对话框");
                //设置对话框显示的内容
                builder.setMessage("一个简单的提示对话框");
                //为对话框设置一个 “确定” 按钮
                builder.setPositiveButton("确定", new Dialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        show.setText("用户单击了确定按钮");
                    }
                });
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        show.setText("用户单击了取消按钮");
                    }
                });
                //创建并显示对话框
                builder.create().show();
            }
        });
    }
}
