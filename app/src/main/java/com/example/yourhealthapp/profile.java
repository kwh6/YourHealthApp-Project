package com.example.yourhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import java.lang.Math;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class profile extends AppCompatActivity {
    Button adding;
    Button main;
    Button profile;
    Button edit;
    int caleaten = 0 ;
    SharedPreferences sp;
    MainActivity info = new MainActivity();
    mainPage fo = new mainPage();
    TextView gain;
    TextView loose;
    double maintanancem;
    TextView maintain;
    double maintainancew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //time
        //remember me
        // men = 10*W + 6.25*H - 5*A + 5 * activity level
        //women= 10*W + 6.25*H - 5*A - 161 * activity level
        //light = 1.2
        //medium = 1.3
        //heavy = 1.4
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences("maintanance", Context.MODE_PRIVATE);
        adding = findViewById(R.id.adding_profile);
        main = findViewById(R.id.main_profile);
        maintain = findViewById(R.id.maintain);
        edit = findViewById(R.id.edit);
        loose = findViewById(R.id.loosing);
        gain = findViewById(R.id.gaining);
        profile = findViewById(R.id.profile_profile);
        Intent intent = getIntent();
        int weight = intent.getIntExtra("weight", 404);
        int height = intent.getIntExtra("height", 404);
        int age = intent.getIntExtra("age", 404);
        maintanancem = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        maintainancew = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        if(info.active == "low physical activity")
        {
            maintainancew *= 1.375;
            maintanancem *= 1.375;
        }
        else if(info.active == "average physical activity")
        {
            maintainancew *= 1.550;
            maintanancem *= 1.550;
        }
        else
        {
            maintainancew *= 1.725;
            maintanancem *= 1.725;
        }
        for (int i = 0 ; i < fo.foods.size(); i++)
        {
            caleaten += fo.foods.get(i).getmCalories() * fo.foods.get(i).getmServings();
        }
        maintanancem -= caleaten;
        maintainancew -= caleaten;
        if (info.g == "male" )
        {
            maintain.setText(Integer.toString((int)maintanancem));
            gain.setText(Integer.toString((int)maintanancem + 500));
            loose.setText(Integer.toString((int) (maintanancem - 500)));
        }
        else
        {
            maintain.setText(Integer.toString((int)maintainancew));
            gain.setText(Integer.toString((int)maintainancew + 500));
            loose.setText(Integer.toString((int)maintainancew - 500));
        }


        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(profile.this, foodadding.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profile.this , MainActivity.class);
                startActivity(i);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(profile.this, mainPage.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(profile.this, profile.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("maintanancem", (float) maintanancem);
        editor.putFloat("maintainancew", (float) maintainancew);
        editor.commit();

    }

}