package com.example.layout;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 滚动视图（ScrollView）的功能和用法
 */
public class DemoScrollView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
    }
}
