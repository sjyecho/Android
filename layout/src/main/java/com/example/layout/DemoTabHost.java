package com.example.layout;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import androidx.annotation.Nullable;

/**
 * 选项卡（TabHost）的功能和用法
 * TabHost可以很方便地在窗口上放置多个标签页，每个标签页相当于获得了一个与外部容器相同大小的组件拜访区域
 * TabHost仅仅是一个简单的容器，它提供了如下两个方法来创建选项卡、添加选项卡
 *      newTabSpec(String tag)：创建选项卡
 *      addTab(TabHost.TapSpec tabSpec)：添加选项卡
 *    使用TabHost的一般步骤为：
 *      1.在界面布局中定义TabHost组件，并为该组件定义该选项卡的内容
 *      2.Activity应该继承TabActivity
 *      3.调用TabActivity的getTabHost()方法获取TabHost对象
 *      4.通过TabHost对象的方法来创建选项卡、添加选项卡
 */
public class DemoTabHost extends TabActivity {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        //设置使用TabHost布局
        LayoutInflater.from(this).inflate(R.layout.tabhost,tabHost.getTabContentView(),true);
        //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话").setContent(R.id.tab01));
        //添加第二个标签页
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("呼出电话",getResources().getDrawable(R.drawable.ic_launcher_background)).setContent(R.id.tab02));
        //添加第三个标签页
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("未接电话").setContent(R.id.tab03));
    }
}
