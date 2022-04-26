package com.example.kaohedemo_4_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CallRecordFragment extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contacts = inflater.inflate(R.layout.call_record_fragment, container, false);
        ArrayList<Map<String,String>> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("name","用户"+(1+i));
            map.put("time","2024-02-09 06:17:39");
            list.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),
                list,
                R.layout.record_item,
                new String[]{"name", "time"},
                new int[]{R.id.name, R.id.time}
        );
        listView=contacts.findViewById(R.id.callrecord_listview);
        listView.setAdapter(simpleAdapter);
        return contacts;
    }

    public void setValue(String text){
        System.out.println(text);
    }
}
