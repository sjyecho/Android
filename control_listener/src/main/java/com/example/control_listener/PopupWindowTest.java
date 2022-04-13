package com.example.control_listener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

/**
 * PopupWindow可以创建类似于对话框风格的窗口，步骤：
 *      1.调用PopupWindow的构造器创建PopupWindow对象
 *      2.调用PopupWindow的showAsDropDown(View v)将PopupWindow作为v组件的下拉组件显示出来；
 *          或调用PopupWindow的showAtLocation方法将PopupWindow在指定位置显示出来
 */
public class PopupWindowTest extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window_test);
        Button button=findViewById(R.id.bn);
        //装载R.layout.popup对应的界面布局
        View root = this.getLayoutInflater().inflate(R.layout.popup, null);
        //创建PopupWindow对象
        final PopupWindow popup=new PopupWindow(root,1100,1100);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //以下拉方式显示
                //popup.showAsDropDown(view);
                //将PopupWindow显示在指定位置
                popup.showAtLocation(button, Gravity.CENTER,20,20);
            }
        });
        //获取Popup窗口中的关闭按钮
        root.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭Popup窗口
                popup.dismiss();
            }
        });
    }
}
