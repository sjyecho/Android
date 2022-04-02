package com.example.contentprovider;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaProviderTest extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaprovidertest);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> descs = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();

        Button view = findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空names、descs、fileNames集合里原有的数据
                names.clear();
                descs.clear();
                fileNames.clear();
                //通过ContentResolver查询所有图片信息
                Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                while (cursor.moveToFirst()) {
                    //获取图片的显示名
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    //获取图片的详细描述
                    String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DESCRIPTION));
                    //获取图片的保存位置的数据
                    byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    //将图片名添加到names集合中
                    names.add(name);
                    descs.add(desc);
                    fileNames.add(new String(data, 0, data.length - 1));
                }
                //创建一个List集合,List集合的元素是Map
                List<Map<String,Object>> listItems=new ArrayList<>();
                //将names、descs两个集合对象的数据转换到Map集合中
                for (int i = 0; i < names.size(); i++) {
                    Map<String,Object> listItem=new HashMap<>();
                    listItem.put("name",names.get(i));
                    listItem.put("desc",descs.get(i));
                    listItems.add(listItem);
                }
                //创建一个SimpleAdapter
                //SimpleAdapter simpleAdapter=new SimpleAdapter(MediaProviderTest.this,listItems,R.layout.line,new String[]{"name","desc"},new int[]{R.id.name,R.id.desc});
                //为show ListView组件设置Adapter
                //show.setAdapter(simpleAdapter);
            }
        });
    }
}
