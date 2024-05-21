package com.example.yourhealthapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner gender;
    public static String[] activity = new String[1];
    public static String[] kind = new String[1];
    public int age;
    public int weight;
    public int height;
    public static final String SHARED_PREFERENCE = "shared pref";
    public static final String w = "weight";
    public static final String h = "height";
    public static final String a = "age";
    public static final String ge = "gender";
    public static final String ac = "activity";
    public String active;
    public String g;
    EditText age_txt;
    EditText height_txt;
    EditText weight_txt;
    ArrayAdapter<CharSequence> kindAdapter;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = new String[1];
        spinner = (Spinner) findViewById(R.id.spinner);
        gender = (Spinner) findViewById(R.id.gender);
        height_txt = (EditText)findViewById(R.id.height);
        weight_txt = (EditText)findViewById(R.id.weight);
        age_txt = (EditText)findViewById(R.id.age);
        kind  = new String[1];
        kindAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        kindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loadData();
        updateViews();
        Button next = findViewById(R.id.next);
        spinner.setAdapter(adapter);
        gender.setAdapter(kindAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kind[0] = parent.getItemAtPosition(position).toString();
                g = kind[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                activity[0] = parent.getItemAtPosition(position).toString();
                active = activity[0];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = Integer.parseInt(weight_txt.getText().toString());
                height = Integer.parseInt(height_txt.getText().toString());
                age = Integer.parseInt(age_txt.getText().toString());
                Intent next_page = new Intent(MainActivity.this,mainPage.class);
                next_page.putExtra("weight", weight);
                next_page.putExtra("height", height);
                next_page.putExtra("age", age);
                saveData();
                startActivity(next_page);
            }
        });
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(w, weight);
        editor.putInt(a, age);
        editor.putInt(h, height);
        editor.putString(ac, active);
        editor.putString(ge, g);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        weight = sharedPreferences.getInt(w, 0);
        height = sharedPreferences.getInt(h, 0);
        age = sharedPreferences.getInt(a, 0);
        active = sharedPreferences.getString(ac, "heavy physical activities");
        g = sharedPreferences.getString(ge, "female");
    }

    public void updateViews() {
        weight_txt.setText(Integer.toString(weight), TextView.BufferType.EDITABLE);
        height_txt.setText(Integer.toString(height), TextView.BufferType.EDITABLE);
        age_txt.setText(Integer.toString(age), TextView.BufferType.EDITABLE);
        activity[0] = active;
        kind[0] = g;
    }
}
