package com.example.firstexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button button2 = findViewById(R.id.button2);

        RadioButton check1 = findViewById(R.id.check1);
        RadioButton check2 = findViewById(R.id.check2);
        RadioButton check3 = findViewById(R.id.check3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle data = new Bundle();
                if (check1.isChecked()) {
                    String c1 = check1.getText().toString();
                    data.putString("data", c1);
                } else if (check2.isChecked()) {
                    String c2 = check2.getText().toString();
                    data.putString("data", c2);
                } else {
                    String c3 = check3.getText().toString();
                    data.putString("data", c3);
                }
                intent.putExtras(data);
                SecondActivity.this.setResult(0, intent);
                SecondActivity.this.finish();
            }
        });
    }
}
