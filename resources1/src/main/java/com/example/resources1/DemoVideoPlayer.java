package com.example.resources1;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import java.io.File;

public class DemoVideoPlayer extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.video);

        VideoView videoView=findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        //File video=new File("/home/ts/AndroidStudioProjects/demo01/resources1/src/main/res/raw");

            videoView.setVideoPath("/storage/emulated/0/Movies/VID_20220421_152936.mp4");
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);
            videoView.requestFocus();

            //System.out.println("----------路径错误----------");

    }
}
