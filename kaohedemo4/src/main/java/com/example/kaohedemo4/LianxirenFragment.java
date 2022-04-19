package com.example.kaohedemo4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * 定义联系人的Fragment
 */
public class LianxirenFragment extends Fragment {

    public LianxirenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lianxiren, container, false);
        Button lianxiren=view.findViewById(R.id.lianxiren);

        //打开联系人界面
        lianxiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}