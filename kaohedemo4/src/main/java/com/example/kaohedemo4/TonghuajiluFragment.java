package com.example.kaohedemo4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * 定义通话记录的Fragment
 */
public class TonghuajiluFragment extends Fragment {

    public TonghuajiluFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tonghuajilu, container, false);
    }
}