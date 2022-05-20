package com.example.control_listener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class DateTimeDialog extends Activity {

    String result;
    String result1;
    String result2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picker_date_time);
        Button dateBn = findViewById(R.id.dateBn);
        Button timeBn = findViewById(R.id.timeBn);
        //为"设置日期"按钮绑定监听器
        dateBn.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            //直接创建一个DatePickerDialog对话框实例，并将它显示出来
            new DatePickerDialog(DateTimeDialog.this,
                    //绑定监听器
                    (datePicker, i, i1, i2) -> {
                        TextView show = findViewById(R.id.show);
                        //show.setText("您选择了：" + i + "年" + i1 + "月" + i2 + "日");
                        result1 = "您选择了：" + i + "年" + i1 + "月" + i2 + "日";
                        show.setText("日期选择完毕，请继续选择时间");
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });
        //为"设置时间"按钮绑定监听器
        timeBn.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(DateTimeDialog.this,
                    (timePicker, i, i1) -> {
                        TextView show = findViewById(R.id.show);
                        result2 = i + "时" + i1 + "分";
                        result = result1 + result2;
                        show.setText(result);
                        //result = "";
                    }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });
    }
}