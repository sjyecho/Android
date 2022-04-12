package com.example.control_listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * 使用AlertDialog创建列表对话框
 */
public class ListDialog extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdialog_demo);
        Button bn = findViewById(R.id.bn);
        TextView show = findViewById(R.id.show);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置对话框的图标
                b.setIcon(R.drawable.toux1);
                //设置对话框的标题
                b.setTitle("简单列表对话框");
                //为对话框设置多个列表
                b.setItems(new String[]{"红色", "绿色", "蓝色"}, new Dialog.OnClickListener() {
                    //该方法的i参数代表用户单击了那个列表项
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //i代表哪个列表项被单击了
                        switch (i)
                        {
                            case 0:
                                show.setBackgroundColor(getResources().getColor(R.color.c1));
                                break;
                            case 1:
                                show.setBackgroundColor(getResources().getColor(R.color.c2));
                                break;
                            case 2:
                                show.setBackgroundColor(getResources().getColor(R.color.c3));
                                break;
                        }
                    }
                });
                //创建并显示对话框
                b.create().show();
            }
        });
    }
}
