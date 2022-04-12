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
 * 使用AlertDialog创建单选列表对话框
 * <p>
 * 只要调用AlertDialog.Builder的setSingleChoiceItems方法即可创建一个单选列表的对话框
 * 本例会采用另一种方式来创建对话框：采用基于Activity回调的方式来开发对话框
 */
public class SingleChoiceDialog extends Activity {

    final int SINGLE_DIALOG = 0x113;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_choice_dialog);
        Button bn = findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(SINGLE_DIALOG);
            }
        });
    }

    //重写onCreateDialog
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        //判断需要生成哪种类型的对话框
        switch (id){
            case SINGLE_DIALOG:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setIcon(R.drawable.toux1);
                b.setTitle("单选列表对话框");
                //为对话框设置多个列表
                b.setSingleChoiceItems(new String[]{"红色","绿色","蓝色"}, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView show = findViewById(R.id.show);
                        //i代表哪个列表项被单击了
                        switch (i){
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
                //添加一个确定按钮，用于关闭该对话框
                b.setPositiveButton("确定",null);
                //创建对话框
                return b.create();
        }
        return null;
    }
}
