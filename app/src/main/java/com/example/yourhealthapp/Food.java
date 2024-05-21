package com.example.yourhealthapp;

public class Food {
    private String mFoodName;
    private int mCalories;
    private int mGramsPerServing;
    private int mServings;
    public Food(String foodName, int calories, int servings, int gramsPerServing)
    {
        mFoodName = foodName;
        mCalories = calories;
        mServings = servings;
        mGramsPerServing = gramsPerServing;
    }

    public int getmCalories() {
        return mCalories;
    }

    public int getmServings() {
        return mServings;
    }

    public String getmFoodName() {
        return mFoodName;
    }

    public int getmGramsPerServing() {
        return mGramsPerServing;
    }
}
