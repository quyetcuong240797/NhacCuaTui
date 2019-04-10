package com.example.nhaccuatui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhaccuatui.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    ImageView imgviewsplash;
    TextView tvnameapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgviewsplash = findViewById(R.id.imgviewsplashimage);
        tvnameapp = findViewById(R.id.tvnameapp);
        tvnameapp.setVisibility(View.INVISIBLE);
        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.amin_alpha);
        final Animation translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        imgviewsplash.startAnimation(alpha);
        imgviewsplash.setVisibility(View.INVISIBLE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 4000);


    }
}
