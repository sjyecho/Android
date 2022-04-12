package com.example.control_listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomListDialog extends Activity {

    final int LIST_DIALOG=0x113;
    //定义三个列表项的名称
    private String[] names={"神族","虫族","人族"};
    //定义三个列表项对应的图标
    private int[] imageIds={R.drawable.toux1,R.drawable.toux2,R.drawable.toux3};
    Button bn;
    TextView show;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_listdialog);
        bn=findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(LIST_DIALOG);
            }
        });

    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        //判断需要生成哪种类型的对话框
        switch (id){
            case LIST_DIALOG:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setIcon(R.drawable.toux1);
                b.setTitle("自定义单选列表对话框");
                //创建一个List集合，List集合的元素是Map
                List<Map<String,Object>> listItems=new ArrayList<>();
                for (int i = 0; i < names.length; i++) {
                    Map<String,Object> listItem=new HashMap<>();
                    listItem.put("header",imageIds[i]);
                    listItem.put("personName",names[i]);
                    listItems.add(listItem);
                }
                //创建一个SimpleAdapter
                SimpleAdapter simpleAdapter=new SimpleAdapter(
                        this,
                        listItems,
                        R.layout.simpleadapter_for_listview,
                        new String[]{"personName","header"},
                        new int[]{R.id.name,R.id.header});
                //为对话框设置多个列表
                b.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        show=findViewById(R.id.show);
                        show.setText("你选的种族是："+names[i]);
                    }
                });
                //创建对话框
                return b.create();
        }
        return null;
    }
}