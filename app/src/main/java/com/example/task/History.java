package com.example.task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class History extends AppCompatActivity {
    LinearLayout sun_card,mon_card,tue_card,wed_card,thu_card,fri_card,sat_card;
    TextView sun_txt,mon_txt,tue_txt,wed_txt,thu_txt,fri_txt,sat_txt;
    TextView sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date;
    ColorStateList oldColor;
    Drawable bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initView();

        oldColor =  sun_txt.getTextColors(); //save original colors
        bg =  sun_card.getBackground(); //save original colors

        buttonFuc();
    }

    private void initView(){
        sun_card=findViewById(R.id.sun_card);
        mon_card=findViewById(R.id.mon_card);
        tue_card=findViewById(R.id.tue_card);
        wed_card=findViewById(R.id.wed_card);
        thu_card=findViewById(R.id.thu_card);
        fri_card=findViewById(R.id.fri_card);
        sat_card=findViewById(R.id.sat_card);

        sun_txt=findViewById(R.id.sun_txt);
        mon_txt=findViewById(R.id.mon_txt);
        tue_txt=findViewById(R.id.tue_txt);
        wed_txt=findViewById(R.id.wed_txt);
        thu_txt=findViewById(R.id.thu_txt);
        fri_txt=findViewById(R.id.fri_txt);
        sat_txt=findViewById(R.id.sat_txt);

        sun_date=findViewById(R.id.sun_date);
        mon_date=findViewById(R.id.mon_date);
        tue_date=findViewById(R.id.tue_date);
        wed_date=findViewById(R.id.wed_date);
        thu_date=findViewById(R.id.thu_date);
        fri_date=findViewById(R.id.fri_date);
        sat_date=findViewById(R.id.sat_date);

    }


    private void buttonFuc(){
        sun_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                sun_card.setBackgroundResource(R.drawable.background_card_stroke);
                sun_txt.setTextColor(getResources().getColor(R.color.orange, null));
                sun_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("sun");

            }
        });

        mon_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mon_card.setBackgroundResource(R.drawable.background_card_stroke);
                mon_txt.setTextColor(getResources().getColor(R.color.orange, null));
                mon_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("mon");

            }
        });

        tue_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                tue_card.setBackgroundResource(R.drawable.background_card_stroke);
                tue_txt.setTextColor(getResources().getColor(R.color.orange, null));
                tue_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("tue");

            }
        });

        wed_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                wed_card.setBackgroundResource(R.drawable.background_card_stroke);
                wed_txt.setTextColor(getResources().getColor(R.color.orange, null));
                wed_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("wed");

            }
        });

        thu_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                thu_card.setBackgroundResource(R.drawable.background_card_stroke);
                thu_txt.setTextColor(getResources().getColor(R.color.orange, null));
                thu_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("thu");

            }
        });

        fri_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                fri_card.setBackgroundResource(R.drawable.background_card_stroke);
                fri_txt.setTextColor(getResources().getColor(R.color.orange, null));
                fri_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("fri");

            }
        });
        sat_card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                sat_card.setBackgroundResource(R.drawable.background_card_stroke);
                sat_txt.setTextColor(getResources().getColor(R.color.orange, null));
                sat_date.setTextColor(getResources().getColor(R.color.orange, null));

                colorRestore("sat");

            }
        });
    }

    private void colorRestore(String name){
        switch (name)
        {
            case "sun":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);



                break;
            case "mon":
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                sun_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "tue":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                sun_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "wed":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                sun_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "thu":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sat_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                sun_card.setBackground(bg);
                fri_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "fri":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sat_date.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);
                sat_txt.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                sun_card.setBackground(bg);
                sat_card.setBackground(bg);
                break;
            case "sat":
                mon_txt.setTextColor(oldColor);
                mon_date.setTextColor(oldColor);
                tue_txt.setTextColor(oldColor);
                tue_date.setTextColor(oldColor);
                wed_txt.setTextColor(oldColor);
                wed_date.setTextColor(oldColor);
                thu_txt.setTextColor(oldColor);
                thu_date.setTextColor(oldColor);
                fri_txt.setTextColor(oldColor);
                fri_date.setTextColor(oldColor);
                sun_txt.setTextColor(oldColor);
                sun_date.setTextColor(oldColor);

                mon_card.setBackground(bg);
                tue_card.setBackground(bg);
                wed_card.setBackground(bg);
                thu_card.setBackground(bg);
                fri_card.setBackground(bg);
                sun_card.setBackground(bg);
                break;
        }
    }



}