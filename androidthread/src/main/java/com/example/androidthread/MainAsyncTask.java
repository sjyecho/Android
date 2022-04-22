package com.example.androidthread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@SuppressWarnings("all")
public class MainAsyncTask extends AppCompatActivity {

    private static final String TAG = "ASYNC_TASK";
    private Button execute;
    private Button cancel;
    private ProgressBar progressBar;
    private TextView textView;
    private MyTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_asynctask);
        execute = findViewById(R.id.execute);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask = new MyTask();
                mTask.execute("http://baidu.com");
                execute.setEnabled(false);
                cancel.setEnabled(true);
            }
        });

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask.cancel(true);
            }
        });
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        //该方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute called");
            textView.setText("loading");
        }

        //doInBackground方法内部执行后台任务，不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground(String... strings) called");
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    long total = entity.getContentLength();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buf = new byte[16];
                    int count = 0;
                    int length = -1;
                    while ((length = is.read(buf)) != -1) {
                        baos.write(buf, 0, length);
                        count += length;
                        //调用publichProgress公布进度，之后onProgressUpdate方法将被执行

                        publishProgress((int) ((count / (float) total) * 100));
                        Thread.sleep(500);
                    }
                    return new String(baos.toByteArray(), "gb2312");
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return null;
        }

        //此方法用于更新进度信息
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            Log.i(TAG,"onProgressUpdate(Integer... progresses) called");
            progressBar.setProgress(progresses[0]);
            textView.setText("loading..."+progresses[0]+"%");
        }

        //用于在执行完后台任务后更新UI，显示结果
        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG,"onPostExecute(String s) called");
            textView.setText(result);

            execute.setEnabled(true);
            cancel.setEnabled(false);
        }

        //用于取消执行任务时更改UI
        @Override
        protected void onCancelled() {
            Log.i(TAG,"onCancelled() called");
            textView.setText("cancelled");
            progressBar.setProgress(0);

            execute.setEnabled(true);
            cancel.setEnabled(false);

        }
    }
}
