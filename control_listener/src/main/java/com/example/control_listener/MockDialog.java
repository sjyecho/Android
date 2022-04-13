package com.example.control_listener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * 对话框风格的窗口
 * 一种自定义对话框的方式，这种对话框本质上依然是窗口
 * 只是把显示窗口的Activity的风格设为对话框风格
 */
public class MockDialog extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mock_dialog);
        Button bn = findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //结束该Activity
                finish();
            }
        });
    }
}
