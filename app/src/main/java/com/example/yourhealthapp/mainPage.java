package com.example.yourhealthapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class mainPage extends AppCompatActivity {
    Button adding;
    Button main;
    Button profile;
    int curDay;
    public static ArrayList<Food> foods = new ArrayList<Food>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Calendar calendar = Calendar.getInstance();
        curDay = 12;
        int rCurDay = calendar.get(Calendar.DAY_OF_MONTH);

        if (rCurDay != curDay)
        {
            remove();
            loadData();
            curDay = rCurDay;
        }
        else
        {
            loadData();
        }
        FoodAdapter adapter = new FoodAdapter(this, foods);
        adapter.notifyDataSetChanged();
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        Intent intent = getIntent();
        int weight = intent.getIntExtra("weight", 404);
        int height = intent.getIntExtra("height", 404);
        int age = intent.getIntExtra("age", 404);
        adding = findViewById(R.id.adding_main);
        main = findViewById(R.id.main_main);
        profile = findViewById(R.id.profile_main);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int item = position;
                new AlertDialog.Builder(mainPage.this).setIcon(android.R.drawable.ic_delete).setTitle("Are you sure ?").setMessage("Do you want to delete this meal ?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foods.remove(item);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("no", null).show();

                return true;
            }
        });
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(mainPage.this, foodadding.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(mainPage.this, mainPage.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_page = new Intent(mainPage.this, profile.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                startActivity(next_page);
            }
        });

    }

    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meals list", null);
        Type type = new TypeToken<ArrayList<Food>>() {}.getType();
        foods = gson.fromJson(json, type);

        if (foods == null)
        {
            foods = new ArrayList<>();
        }
    }
    public void remove()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("meals list");
    }

}