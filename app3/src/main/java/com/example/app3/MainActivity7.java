package com.example.app3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

/**
 * 使用ToggleButton动态改变布局
 */
public class MainActivity7 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main7);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);
        final LinearLayout test = (LinearLayout) findViewById(R.id.test);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //设置LinearLayout垂直布局
                    test.setOrientation(LinearLayout.VERTICAL);
                }else {
                    //设置LinearLayout水平布局
                    test.setOrientation(LinearLayout.HORIZONTAL);
                }
            }
        });
    }
}
