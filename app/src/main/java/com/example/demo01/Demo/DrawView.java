package com.example.demo01.Demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 自定义组件，继承View基类，并重写onDraw方法，该方法负责在该组件的指定位置绘制一个小球。
 * 有了这个自定义组件之后，可以通过Java代码把该组件添加到指定容器中
 */
public class DrawView extends View {

    public float currentX=40;
    public float currentY=50;

    /**
     *
     * @param context
     */
    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint p=new Paint();
        p.setColor(Color.RED);
        canvas.drawCircle(currentX,currentY,15,p);
    }
}
