package com.example.layout;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 表格布局
 * 用户友好的输入界面
 */
public class DemoTableLayout extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelayout);
    }
}
