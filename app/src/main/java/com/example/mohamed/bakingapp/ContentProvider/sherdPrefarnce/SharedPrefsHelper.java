package com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.mohamed.bakingapp.model.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by mohamed on 14/09/2017.
 */

public class SharedPrefsHelper {
    private SharedPreferences mSharedPreferences;
    public static final String MY_PREFS = "MY_PREFS";
    public static final String TYPE = "EMAIL";

    public SharedPrefsHelper(Context context){
     mSharedPreferences=context.getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);
    }

    public void Clear(){
        mSharedPreferences.edit().clear().apply();
    }

    public void PutMealName(String name){
        Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString(TYPE, name);
        prefsEditor.commit();
    }

    public String getMealName(){
        String name = mSharedPreferences.getString(TYPE, "");
         return name;
    }



}
