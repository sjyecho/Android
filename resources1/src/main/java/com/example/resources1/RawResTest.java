package com.example.resources1;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.io.IOException;

public class RawResTest extends Activity {

    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rawrestest);
        //直接根据声音的id来创建MediaPlayer
        mediaPlayer1 = MediaPlayer.create(this, R.raw.bomb);
        //获取该应用的AssetManager
        AssetManager am = getAssets();
        try {
            //获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("bomb.mp3");
            mediaPlayer2 = new MediaPlayer();
            //使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor());
            mediaPlayer2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取第一个按钮，并为它绑定事件监听
        Button playRaw = findViewById(R.id.playRaw);
        playRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer1.start();
            }
        });

        //获取第二个按钮，并为它绑定事件监听
        Button playAsset = findViewById(R.id.playAsset);
        playAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer1.pause();
                //mediaPlayer2.start();
            }
        });
    }
}
