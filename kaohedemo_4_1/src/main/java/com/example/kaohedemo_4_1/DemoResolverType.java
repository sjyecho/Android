package com.example.kaohedemo_4_1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 类别查询
 */
public class DemoResolverType extends AppCompatActivity {

    ContentResolver contentResolver;
    Button insert, search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demoresolvertype);
        insert=findViewById(R.id.insert);
        search=findViewById(R.id.search);
        contentResolver = getContentResolver();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sjmc = ((EditText)findViewById(R.id.sjmc)).getText().toString();
                String lbmc = ((EditText)findViewById(R.id.lbmc)).getText().toString();
                ContentValues values=new ContentValues();
                values.put(StaticUri.TableColumns.TYPE_SJMC,sjmc);
                values.put(StaticUri.TableColumns.TYPE_LBMC,lbmc);
                contentResolver.insert(StaticUri.TableColumns.TYPE_INSERT,values);
                ((EditText)findViewById(R.id.sjmc)).setText("");
                ((EditText)findViewById(R.id.lbmc)).setText("");
                Toast.makeText(DemoResolverType.this,"添加类别成功",Toast.LENGTH_SHORT).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=((EditText) findViewById(R.id.key)).getText().toString();
                Cursor cursor=contentResolver.query(StaticUri.TableColumns.TYPE_QUERY,null,"sjmc like ? or lbmc like ?",new String[]{"%"+key+"%","%"+key+"%"},null);
                Bundle data=new Bundle();
                data.putSerializable("data",converCursorToList(cursor));
                Intent intent = new Intent(DemoResolverType.this,ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }
    private ArrayList<Map<String,String>> converCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> result=new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            map.put(StaticUri.TableColumns.TYPE_SJMC,cursor.getString(1));
            map.put(StaticUri.TableColumns.TYPE_LBMC,cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}
