package com.example.kaohedemo2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class kaohedemo extends Activity {

    int[] textIds = new int[]{
            R.string.c1, R.string.c2, R.string.c3,
            R.string.c4, R.string.c5, R.string.c6,
            R.string.c7, R.string.c8, R.string.c9,
    };

    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaohedemo);
        setTheme(R.style.Theme);
        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return textIds.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView text = new TextView(kaohedemo.this);
                Resources res = kaohedemo.this.getResources();
                text.setWidth((int) res.getDimension(R.dimen.cell_width));
                text.setHeight((int) res.getDimension(R.dimen.cell_height));
                text.setText(textIds[i]);
                @SuppressLint("Recycle") TypedArray icons = res.obtainTypedArray(R.array.plain_arr);
                text.setBackgroundDrawable(icons.getDrawable(i));
                text.setTextSize(20);
                return text;
            }
        };
        GridView grid = findViewById(R.id.grad01);
        grid.setAdapter(ba);

        mediaPlayer = MediaPlayer.create(this, R.raw.bomb);
        Button playRaw = findViewById(R.id.playRaw);
        playRaw.setOnClickListener(view -> mediaPlayer.start());
    }
}