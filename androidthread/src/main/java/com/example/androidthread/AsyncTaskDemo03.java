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
import java.util.ArrayList;
import java.util.List;

/**
 * 异步加载多张图片
 */
@SuppressWarnings("all")
public class AsyncTaskDemo03 extends Activity {

    private ProgressBar progressBar;
    private ImageView imageView1;
    private ImageView imageView2;
    private final String[] imgUrls = {"https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF", "https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF"};
    private final List<Bitmap> list = new ArrayList<>();
    private Bitmap bitmap = null;
    private MyAsyncTask myTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_demo03);
        progressBar = findViewById(R.id.progressbar);
        imageView1 = findViewById(R.id.imageview1);
        imageView2 = findViewById(R.id.imageview2);
        myTask = new MyAsyncTask();
        //execute：开始执行异步处理任务
        myTask.execute(imgUrls);
    }

    //将AsyncTask和Activity的生命周期绑定，暂停状态时终止异步操作
    @Override
    protected void onPause() {
        super.onPause();
        if (myTask!=null&&myTask.getStatus()==AsyncTask.Status.RUNNING){
            //cancel()方法只是将对应的AsyncTask标记为cancel状态，并没有真正取消线程的执行
            myTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, List<Bitmap>> {

        //准备阶段让进度条显示
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        //网络获取图片，返回值类型为List集合
        //for循环遍历可变参数，拿到单次任务所需的图片路径
        @Override
        protected List<Bitmap> doInBackground(String... strings) {
            for (int i = 0; i < strings.length; i++) {
                if (isCancelled()){
                    break;
                }
                publishProgress(50);
                String newurl = strings[i];
                URLConnection urlConnection;
                InputStream is;
                try {
                    urlConnection = new URL(newurl).openConnection();
                    is = urlConnection.getInputStream();
                    BufferedInputStream bf = new BufferedInputStream(is);
                    bitmap = BitmapFactory.decodeStream(bf);
                    Thread.sleep(1000);
                    list.add(bitmap);
                    is.close();
                    bf.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
            progressBar.setVisibility(View.GONE);
            imageView1.setImageBitmap(bitmaps.get(0));
            imageView2.setImageBitmap(bitmaps.get(1));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
