package com.example.androidthread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 加载单张图片
 */
public class AsyncTaskDemo02 extends Activity {

    ImageView imageView;
    ProgressBar progressBar;
    String URL = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_demo02);
        imageView = findViewById(R.id.imageview);
        progressBar = findViewById(R.id.progressbar);
        //开始异步加载图片
        new MyAsyncTask().execute(URL);

    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //准备阶段让进度条显示
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        //网络获取图片
        @Override
        protected Bitmap doInBackground(String... strings) {
            //从可变参数的数组中拿到第0位的图片地址
            String newurl = strings[0];
            Bitmap bitmap = null;
            URLConnection urlConnection;
            InputStream is;
            //图片下载操作
            try {
                urlConnection = new URL(newurl).openConnection();
                is = urlConnection.getInputStream();
                BufferedInputStream bf = new BufferedInputStream(is);
                Thread.sleep(1000);
                bitmap= BitmapFactory.decodeStream(bf);
                is.close();
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //返回结果bitmap
            return bitmap;
        }

        //拿到bitmap图片，更新UI
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
