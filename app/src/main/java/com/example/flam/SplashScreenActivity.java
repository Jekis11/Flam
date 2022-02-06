package com.example.flam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    TextView txtFlam, txtFlam2;
    RelativeLayout relativeLayout;
    Animation txtAnimation, layoutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txtAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fall_down);
        layoutAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.bottom_to_top);

        txtFlam = findViewById(R.id.txtscreen1);
        txtFlam2 = findViewById(R.id.txtscreen2);
        relativeLayout = findViewById(R.id.relMaim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layoutAnimation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         txtFlam.setVisibility(View.VISIBLE);
                         txtFlam2.setVisibility(View.VISIBLE);

                         txtFlam.setAnimation(txtAnimation);
                         txtFlam2.setAnimation(txtAnimation);

                    }
                },900);

            }
        },500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);

            }
        },6000);


    }
}