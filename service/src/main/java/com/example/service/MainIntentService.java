package com.example.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MainIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public MainIntentService(String name) {
        super(name);
    }

    public MainIntentService() {
        super(null);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("----------IntentService当前线程为----------"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("----------Intent已被销毁----------");
    }
}
