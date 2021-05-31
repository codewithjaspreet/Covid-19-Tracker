package com.jaspreetsingh.CovidTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.jaspreetsingh.travelmania.R;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    RelativeLayout splash;
    Animation txtanim,layoutanim;
    TextView corona,tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        corona = findViewById(R.id.corona);
        tracker  =findViewById(R.id.tracker);
        splash = findViewById(R.id.splashlayout);
        txtanim = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.falldown);
        layoutanim = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bottom_to_top);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.setVisibility(View.VISIBLE);
                splash.setAnimation(layoutanim);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        corona.setVisibility(View.VISIBLE);
                        tracker.setVisibility(View.VISIBLE);
                        corona.setAnimation(txtanim);
                        tracker.setAnimation(txtanim);


                    }
                },900);

            }
        },500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(SplashScreen.this,OnboardingActivity.class);
                startActivity(intent);
                finish();
            }
        },6000);
    }

}