package com.jaspreetsingh.CovidTracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaspreetsingh.travelmania.R;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout layoutdots;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Animation animation;
    Button letsgetstarted;
    int currrentposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = findViewById(R.id.slider);
        layoutdots = findViewById(R.id.dots);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        letsgetstarted = findViewById(R.id.ltsgetstarted);
        adddots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }

    private void adddots(int position){
        dots  =new TextView[4];
        layoutdots.removeAllViews();
        for (int i = 0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            layoutdots.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adddots(position);
            currrentposition = position;
            if (position == 0){
                letsgetstarted.setVisibility(View.INVISIBLE);

            }
            else if (position == 1){
                letsgetstarted.setVisibility(View.INVISIBLE);

            }
            else if (position == 2){
                letsgetstarted.setVisibility(View.INVISIBLE);

            }
            else {
                animation = AnimationUtils.loadAnimation(OnboardingActivity.this,R.anim.bottom_anim);
                letsgetstarted.setAnimation(animation);
                letsgetstarted.setVisibility(View.VISIBLE);
                letsgetstarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OnboardingActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    public void next(View view){
        viewPager.setCurrentItem(currrentposition+1);


    }
}