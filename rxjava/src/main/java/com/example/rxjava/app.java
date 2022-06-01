package com.example.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName app
 * @Description TODO
 * @Auther Shui Junyang
 * @Date 2022/5/31 下午2:05
 * @Version 1.0
 */
public class app extends Activity {

    private static final String TAG = "apppp";

    Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //此create  return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
        Observable.create(new ObservableOnSubscribe<String>() {

                    //自定义source
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("连载1");//从这开始拆包裹
                        emitter.onNext("连载2");//emitter相当于一层包裹
                        emitter.onNext("连载3");//等于执行ObservableCreate类中ObservableEmitter.onNext
                        //emitter.onNext("完结");
                        emitter.onComplete();
                    }
                })
//                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
//                .subscribeOn(Schedulers.io())//执行在io线程
                //ObservableCreate.subscribe
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        if ("完结".equals(value)) {
                            mDisposable.dispose();
                            return;
                        }
                        Log.d(TAG, "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError=" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }
                });
    }
}
