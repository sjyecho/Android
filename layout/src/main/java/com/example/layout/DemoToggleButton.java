package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

/**
 * 状态开关按钮的功能与用法
 */
public class DemoToggleButton extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.togglebutton);

        ToggleButton toggle = findViewById(R.id.toggle);
        final LinearLayout test = findViewById(R.id.test);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //设置LinearLayout垂直布局
                    test.setOrientation(LinearLayout.HORIZONTAL);
                } else {
                    //设置LinearLayout水平布局
                    test.setOrientation(LinearLayout.VERTICAL);
                }
            }
        });
    }
}
