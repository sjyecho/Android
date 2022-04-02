package com.example.activitys;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SelectCityActivity extends ExpandableListActivity {

    private String[] province=new String[]{
            "广东","广西","湖南"
    };
    private String[][] cities=new String[][]{
            {"合肥","安庆"},
            {"北京","上海"},
            {"三亚","长沙"}
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter=new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return province.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return cities[i].length;
            }

            //获取指定组位置处的组数据
            @Override
            public Object getGroup(int i) {
                return province[i];
            }

            //获取指定组位置、指定子列表项处的子列表项数据
            @Override
            public Object getChild(int i, int i1) {
                return cities[i][i1];
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            private TextView getTextView(){
                AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,64);
                TextView textView=new TextView(SelectCityActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout ll=new LinearLayout(SelectCityActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo=new ImageView(SelectCityActivity.this);
                ll.addView(logo);
                TextView textView=getTextView();
                textView.setText(getGroup(i).toString());
                ll.addView(textView);
                return ll;
            }

            //该方法决定每个子选项的外观
            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                TextView textView=getTextView();
                textView.setText(getChild(i,i1).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };

        //设置该窗口显示列表
        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //获取启动该Activity之前的Activity对应的Intent
                Intent intent =getIntent();
                Bundle data=new Bundle();
                data.putString("city",cities[i][i1]);
                intent.putExtras(data);
                //设置该SelectActivity的结果码,并设置结束之后退回的Activity
                SelectCityActivity.this.setResult(0,intent);
                //结束SelectCityActivity
                SelectCityActivity.this.finish();
                return false;
            }
        });
    }
}
