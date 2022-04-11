package com.example.resources1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义一个View组件
 */
public class AlphaImageView extends androidx.appcompat.widget.AppCompatImageView {

    //图像透明度每次改变的大小
    private int alphaDelta = 0;
    //记录图片当前的透明度
    private int curAlpha = 0;
    //每隔多少毫秒透明度改变一次
    private final int SPEED = 300;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x123) {
                //每次增加curAlpha的值
                curAlpha += alphaDelta;
                if (curAlpha > 255) {
                    curAlpha = 255;
                }
                AlphaImageView.this.setAlpha(curAlpha);
            }
        }
    };

    public AlphaImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphaImageView);
        //获取duration参数
        int duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0);
        //计算图像透明度每次改变的大小
        alphaDelta = 255 * SPEED / duration;
    }

    /**
     * 该方法启动了一个任务调度来控制图片透明度的改变
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setAlpha(curAlpha);
        final Timer timer = new Timer();
        //按固定间隔发送消息，通知系统改变图片的透明度
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x123;
                if (curAlpha >= 255) {
                    timer.cancel();
                } else {
                    handler.sendMessage(msg);
                }
            }
        }, 0, SPEED);
    }
}
