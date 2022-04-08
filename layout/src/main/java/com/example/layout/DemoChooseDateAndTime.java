package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.util.Calendar;

/**
 * 选择日期和时间,并显示出来
 */
public class DemoChooseDateAndTime extends Activity {

    //定义五个记录当前时间的变量
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private void showDate(int year, int month, int day,int hour,int minute) {
        EditText show = findViewById(R.id.show_date);
        show.setText("您选择的日期为：" + year + "年" + month + "月" + day + "日"+hour+"时"+minute+"分");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateandtime);
        DatePicker datePicker = findViewById(R.id.datepicker);
        TimePicker timePicker = findViewById(R.id.timepicker);
        //获取当前的年月日、小时分钟
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        //初始化DatePicker组件，初始化时指定监听器
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                DemoChooseDateAndTime.this.year = year;
                DemoChooseDateAndTime.this.month = month;
                DemoChooseDateAndTime.this.day = day;
                //显示当前日期、时间
                showDate(year, month, day, hour, minute);
                //showDate(year, month, day);
            }
        });

        //为TimePicker指定监听器
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                DemoChooseDateAndTime.this.hour = hour;
                DemoChooseDateAndTime.this.minute = minute;
                //显示当前日期、时间
                showDate(year, month, day, hour, minute);
            }
        });
    }
}
