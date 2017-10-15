package com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce;

import com.example.mohamed.bakingapp.model.Meal;

import java.util.List;

/**
 * Created by mohamed on 15/09/2017.
 */

public class DataManager {
    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.Clear();
    }
    public void saveMealName(String meals) {
        mSharedPrefsHelper.PutMealName(meals);
    }

    public String getMealName() {
        return mSharedPrefsHelper.getMealName();
    }



}
