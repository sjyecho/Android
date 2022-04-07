package com.example.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * 图像视图（ImageView）的功能和用法
 * 图片浏览器：本例的图片浏览器可以改变所查看图片的透明度，可以查看图片的局部细节
 */
@SuppressWarnings("all")
public class DemoImageView extends Activity {

    //定义一个访问图片的数组
    int[] images = new int[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3
    };
    //定义默认显示的图片
    int currentImg = 0;
    //定义图片的初始透明度
    private int alpha = 255;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button next = findViewById(R.id.next);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);

        //定义查看下一张图片的监听器
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentImg >= 2) {
                    currentImg = 0;
                }else {
                    currentImg++;
                }
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
                //如果图片还未回收，先强制回收该图片
                if (!bitmapDrawable.getBitmap().isRecycled()) {
                    bitmapDrawable.getBitmap().recycle();
                }
                //改变ImageView显示的图片
                image1.setImageBitmap(BitmapFactory.decodeResource(getResources(), images[currentImg]));
            }
        });
        //定义改变图片透明度的方法
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == plus) {
                    alpha += 20;
                }
                if (view == minus) {
                    alpha -= 20;
                }
                if (alpha >= 255) {
                    alpha = 255;
                }
                if (alpha <= 0) {
                    alpha = 0;
                }
                //改变图片的透明度
                image1.setAlpha(alpha);
            }
        };
        //为两个按钮添加监听器
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image1.setOnTouchListener(new View.OnTouchListener() {
            /*
                @SuppressLint("ClickableViewAccessibility")
                这个警告是说,有可能会和点击事件发生冲突
                如果在touch中返回了true,那么就不会响应onClick事件了
                必须调用一下view.performClick(),才会触发
            */
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
                //获取第一个图片显示框中的位图
                Bitmap bitmap = bitmapDrawable.getBitmap();
                //bitmap图片实际大小与第一个ImageView的缩放比例
                double scale = bitmap.getWidth() / 320.0;
                //获取需要显示的图片的开始点
                int x = (int) (motionEvent.getX() * scale);
                int y = (int) (motionEvent.getY() * scale);
                if (x + 120 > bitmap.getWidth()) {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight()) {
                    y = bitmap.getHeight() - 120;
                }
                //显示图片的指定区域
                //Bitmap是代表位图的类，调用它的createBitmap（）静态方法即可截取位图的指定部分--返回截取区域生成的新位图
                image2.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 120, 120));
                image2.setAlpha(alpha);
                return true;//return true则不响应单击事件
            }
        });
    }
}
