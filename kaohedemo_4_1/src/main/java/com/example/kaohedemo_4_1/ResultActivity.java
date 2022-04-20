package com.example.kaohedemo_4_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultactivity);
        ContentResolver contentResolver = getContentResolver();
        ListView listView = findViewById(R.id.listview);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable("data");
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                ResultActivity.this,
                list,
                R.layout.item,
                new String[]{"sjmc", "rksj","jg"},
                new int[]{R.id.head, R.id.name, R.id.phone});

        listView.setAdapter(simpleAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //单击条目进行修改或者删除
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Map<Integer, Integer> map = view.getAttributeSourceResourceMap();
                TextView textView_name = view.findViewById(R.id.sjmc);
                String delete_name = textView_name.getText().toString();
                //System.out.println(delete_name+"-----------------------------------------------");
                builder.setTitle("修改或删除");
                TableLayout updateForm = (TableLayout) getLayoutInflater().inflate(R.layout.update_layout, null);
                EditText sjmc = updateForm.findViewById(R.id.sjmc);
                EditText rksj = updateForm.findViewById(R.id.rksj);
                EditText price = updateForm.findViewById(R.id.price);
                builder.setView(updateForm);
                builder.setPositiveButton("确认修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //修改操作
                        String update_sjmc = sjmc.getText().toString();
                        String update_rksj = rksj.getText().toString();
                        String update_price = price.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put(StaticUri.TableColumns.CONTACTS_NAME, update_sjmc);
                        values.put(StaticUri.TableColumns.CONTACTS_PHONE, update_rksj);
                        //values.put(StaticUri.TableColumns.BOOK_JG, update_price);
                        contentResolver.update(StaticUri.TableColumns.CONTACTS_UPDATE, values,"sjmc=? and rksj=? and jg=?", new String[]{update_sjmc, update_rksj,update_price});
                    }
                });
                builder.setNegativeButton("删除此数据", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //删除操作
                        contentResolver.delete(StaticUri.TableColumns.CONTACTS_DELETE, "sjmc=?",new String[]{delete_name});
                        listView.notify();
                    }
                });
                builder.create().show();
            }
        });
    }
}
