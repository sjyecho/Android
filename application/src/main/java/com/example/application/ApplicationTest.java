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
 * 每个Android App运行时,会首先自动创建Application类并实例化Application对象,且只有一个
 */
public class ApplicationTest extends Application {

    private static final String TGA="sjy:";

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * Android系统的入口是Application类的onCreate(),默认为空实现
     *
     * 作用:
     *      初始化应用程序级别的资源,如全局对象、环境配置变量、图片资源初始化、推送服务的注册等
     *      数据共享、数据缓存
     */
    @Override
    public void onCreate() {
        super.onCreate();
        registerComponentCallbacks(new ComponentCallbacks2() {
            /**
             * 通知应用程序，当前内存使用情况（以内存级别进行识别）
             * @param i
             */
            @Override
            public void onTrimMemory(int i) {

            }

            /**
             * 监听应用程序配置信息的改变，如屏幕旋转等
             * 调用时刻：应用程序配置信息改变时调用
             * @param configuration
             */
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

        /**
         * 注册对应用程序内所有Activity的生命周期监听
         */
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                Log.d(TGA,"--onActivityCreated--"+activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d(TGA,"--onActivityStarted--"+activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d(TGA,"--onActivityResumed--"+activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d(TGA,"--onActivityPaused--"+activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d(TGA,"--onActivityStopped--"+activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                Log.d(TGA,"--onActivitySaveInstanceState--"+activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TGA,"--onActivityDestroyed--"+activity.getLocalClassName());
            }
        });
    }
}
