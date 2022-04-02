package com.example.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

/**
 * 使用两个Activity
 * 从第二个Activity中选择信息,并返回给第一个Activity
 */
public class ActivityForResult extends Activity {

    Button bn;
    EditText city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityforresult);
        bn=(Button) findViewById(R.id.bn);
        city=(EditText) findViewById(R.id.city);
        //为按钮绑定单击事件
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建对应目标Activity的Intent
                Intent intent=new Intent(ActivityForResult.this,SelectCityActivity.class);
                //启动指定Activity并等待返回的结果,其中0是请求码,用于标识该请求
                startActivityForResult(intent,0);
            }
        });
    }

    //重写该方法,该方法以回调的方式来获取指定Activity返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //当requestCode、ResultCode同时为0时,也就是处理特定的结果
        if (requestCode==0&&resultCode==0){
            //取出Intent里的Extras数据
            Bundle data=intent.getExtras();
            //取出Bundle中的数据
            String resultCity = data.getString("city");
            city.setText(resultCity);
        }
    }
}
