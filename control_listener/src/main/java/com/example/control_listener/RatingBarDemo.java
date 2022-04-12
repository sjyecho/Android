package com.example.control_listener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.Nullable;

/**
 * 星级评分条(RatingBar)的功能和用法
 * <p>
 * XML属性
 * android:isIndicator         设置该星级评分条是否允许用户改变(true为不允许修改)
 * android:numStars            设置该星级评分条总共有多少个星级
 * android:rating              设置该星级评分条默认的星级
 * android:stepSize            设置每次最少需要改变多少个星级
 */
public class RatingBarDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar_demo);
        RatingBar ratingBar = findViewById(R.id.rating);
        ImageView image = findViewById(R.id.image);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                image.setAlpha((int) (v * 255 / 5));
            }
        });
    }
}
