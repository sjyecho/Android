package com.example.kaohedemo4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * 定义拨号的Fragment
 */
public class BohaoFragment extends Fragment {

    public BohaoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bohao, container, false);
        Button bohao = view.findViewById(R.id.bohao);
        bohao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}