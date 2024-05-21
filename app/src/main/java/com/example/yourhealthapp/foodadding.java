package com.example.yourhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class foodadding extends AppCompatActivity {

    Button adding;
    Button main;
    Button profile;
    Button add;
    Button dont;
    EditText calories;
    EditText servings;
    EditText grams;
    EditText name;
    mainPage array = new mainPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodadding);
        adding = findViewById(R.id.adding_food);
        main = findViewById(R.id.main_food);
        profile = findViewById(R.id.profile_food);
        add = findViewById(R.id.add);
        dont = findViewById(R.id.dont);
        Intent i = getIntent();
        int weight = i.getIntExtra("weight", 404);
        int height = i.getIntExtra("height", 404);
        int age = i.getIntExtra("age", 404);
        grams = findViewById(R.id.grams);
        calories = findViewById(R.id.calories);
        servings = findViewById(R.id.servings);
        name = findViewById(R.id.name);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food w = new Food(name.getText().toString(), Integer.parseInt(calories.getText().toString()) , Integer.parseInt(servings.getText().toString()), Integer.parseInt(grams.getText().toString()));
                array.foods.add(w);
                saveData();
            }
        });
        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = name.getText().toString();
                q = "How many calories there are in one serving of" + q;
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(foodadding.this, foodadding.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(foodadding.this, mainPage.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(foodadding.this, profile.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        saveData();

    }
    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array.foods);
        editor.putString("meals list", json);
        editor.apply();
    }

}