package com.example.kaohedemo5;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super(null);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("----------IntentService当前线程为----------"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("----------IntentService已被销毁,此线程为----------"+Thread.currentThread().getId());
    }
}
