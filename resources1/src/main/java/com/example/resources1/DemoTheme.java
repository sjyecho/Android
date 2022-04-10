package com.example.resources1;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DemoTheme extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme);
        setContentView(R.layout.demostyle);
    }
}
