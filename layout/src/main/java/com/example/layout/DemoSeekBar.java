package com.example.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

/**
 * 拖动条（SeekBar）的功能和用法
 * 拖动条和进度条非常相似，只是进度条采用颜色填充来表明进度完成的程度，而拖动条则通过滑块的位置来标识数值
 * 拖动条允许用户拖动滑块来改变值，因此拖动条通常用于对系统的某种数值进行调节，比如调节音量等
 * 改变滑块外观通过如下属性来指定：
 * android:thumb ：指定一个Drawable对象。该对象将作为自定义滑块
 * 为了让程序能响应拖动条滑块位置的改变，程序可以考虑为它绑定一个OnSeekBarChangeListener监听器
 */
public class DemoSeekBar extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar);
        ImageView image = findViewById(R.id.image);
        SeekBar seekBar = findViewById(R.id.seekbar);
        RatingBar ratingBar = findViewById(R.id.rating);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //当拖动条的滑块位置发生改变时触发该方法
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                image.setAlpha(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //动态改变图片的透明度，其中255是星级评分条的最大值
                //5个星星就代表最大值255
                image.setAlpha((int) (v * 255 / 5));
            }
        });
    }
}
