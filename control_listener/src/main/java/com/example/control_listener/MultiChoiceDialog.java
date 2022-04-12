package com.example.control_listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * 使用AlertDialog创建多选列表对话框
 * <p>
 * 只要调用AlertDialog.Builder的setMultiChoiceItems方法即可创建一个多选列表的对话框
 */
public class MultiChoiceDialog extends Activity {

    final int SINGLE_DIALOG = 0x113;

    //设置初始化时选中哪些列表项
    final boolean[] checkStatus = new boolean[]{true, false, true};
    TextView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_choice_dialog);
        Button bn = findViewById(R.id.bn);
        show = findViewById(R.id.show);
        show.setText("显示区域");
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(SINGLE_DIALOG);
            }
        });
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        //判断需要生成哪种类型的对话框
        switch (id){
            case SINGLE_DIALOG:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("选择你喜欢的颜色");
                b.setIcon(R.drawable.toux1);
                String[] arr = new String[]{"红色", "绿色", "蓝色"};
                b.setMultiChoiceItems(arr,
                        //设置默认勾选了哪些列表项
                        checkStatus,
                        //为列表项的单击事件设置监听器
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                show = findViewById(R.id.show);
                                String result = "您喜欢的颜色为：";
                                for (int i1 = 0; i1 < checkStatus.length; i1++) {
                                    if (checkStatus[i1]) {
                                        result += arr[i1];
                                    }
                                }
                                show.setText(result);
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
