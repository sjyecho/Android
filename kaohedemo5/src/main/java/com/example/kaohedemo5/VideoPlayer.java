package com.example.kaohedemo5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class VideoPlayer extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        VideoView videoView=findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.abc);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);

        videoView.requestFocus();
    }
}
