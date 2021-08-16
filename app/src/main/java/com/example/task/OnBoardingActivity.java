package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.adapters.OnBoardingAdapter;
import com.example.task.models.OnBoardingModel;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager splashViewPager;
    OnBoardingAdapter adapter;
    private List<OnBoardingModel> listItems;
    int pageNo=0;
    TextView text_Skip;
    Button btn_GetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        btn_GetStarted = findViewById(R.id.btn_GetStarted);
        text_Skip = findViewById(R.id.text_Skip);
        text_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, WelcomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        splashViewPager = findViewById(R.id.splash_ViewPager);
        showIntroSlides(splashViewPager);
        splashViewPager.addOnPageChangeListener(viewListener);
    }

    private void showIntroSlides(ViewPager viewPager) {
        listItems = new ArrayList<>();
        listItems.add(new OnBoardingModel(R.drawable.ic_accept_a_job, "Accept a Job"));
        listItems.add(new OnBoardingModel(R.drawable.ic_tracking_real_time, "Tracking Realtime"));
        listItems.add(new OnBoardingModel(R.drawable.ic_earn_money, "Earn Money"));
        adapter = new OnBoardingAdapter(this, listItems);
        viewPager.setAdapter(adapter);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pageNo = position;
            if(position == 2){
                text_Skip.setVisibility(View.GONE);
                btn_GetStarted.setVisibility(View.VISIBLE);
                btn_GetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OnBoardingActivity.this, WelcomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            } else {
                text_Skip.setVisibility(View.VISIBLE);
                btn_GetStarted.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}