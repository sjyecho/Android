package com.example.application;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Application继承自ContextWarpper类，也属于Android中的一个系统组件
 *
 * Application类的应用场景有（按优先级排序）：
 *  初始化应用程序级别的资源，如全局对象，环境配置变量等
 *  数据共享、数据缓存，如设置全局共享变量、方法等
 *  获取应用程序当前的内存使用情况，及时释放资源，从而避免被系统杀死
 *  监听应用程序配置信息的改变，如屏幕旋转等
 *  监听应用程序内所有Activity的生命周期
 */
public class NewApplication extends Application {

    private static final String VALUE = "SJY";
    private String init = "123";
    private final static String TAG = "SJY";

    /**
     * Android系统的入口是Application类的onCreate()，默认为空实现。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //注册对应用程序内所有Activity的生命周期监听
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                Log.d(TAG, "onActivityCreated:" + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d(TAG, "onActivityStarted:" + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d(TAG, "onActivityResumed:" + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d(TAG, "onActivityPaused:" + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d(TAG, "onActivityStopped:" + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                Log.d(TAG, "onActivitySaveInstanceState:" + activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TAG, "onActivityDestoryed:" + activity.getLocalClassName());
            }
        });

        /**
         * 注册ComponentCallbacks2回调接口
         * 本质是复写ComponentCallbacks2回调接口里的方法从而实现更多的操作
         */
        registerComponentCallbacks(new ComponentCallbacks2() {

            /**
             * 通知应用程序当前内存使用情况
             * @param level
             */
            @Override
            public void onTrimMemory(int level) {

            }

            /*
                该配置信息是指：Manifest.xml文件下的Activity标签属性android：configChanges的值
                Activity在配置中的属性改变时，不重启Activity，只执行onConfigurationChanged()
            * */
            @Override
            public void onConfigurationChanged(@NonNull Configuration configuration) {

            }

            /**
             * 监听Android系统整体内存较低时刻
             */
            @Override
            public void onLowMemory() {

            }
        });
    }
}
