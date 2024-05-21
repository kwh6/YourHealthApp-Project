package com.example.yourhealthapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    private static final String LOG_TAG = FoodAdapter.class.getSimpleName();

    public FoodAdapter(Context context, ArrayList<Food> food) {
        super(context, 0, food);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.the_list, parent, false);
        }

        Food currentFood = getItem(position);

        TextView grm = (TextView) listItemView.findViewById(R.id.grams_per);

        grm.setText(Integer.toString(currentFood.getmGramsPerServing()) + "g");

        TextView name = (TextView) listItemView.findViewById(R.id.name_list);

        name.setText(currentFood.getmFoodName());

        TextView cal = (TextView) listItemView.findViewById(R.id.calories_per);

        cal.setText(Integer.toString(currentFood.getmCalories()) + "kcal");

        TextView ser = (TextView) listItemView.findViewById(R.id.serving);
        ser.setText(Integer.toString((currentFood.getmServings())) + " servings");
        return listItemView;
    }
}
