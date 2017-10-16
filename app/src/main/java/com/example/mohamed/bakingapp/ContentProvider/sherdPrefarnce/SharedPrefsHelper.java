package com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.mohamed.bakingapp.model.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public <T> void PutMealName(List<T> list){
        Editor prefsEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefsEditor.putString(TYPE,json);
        prefsEditor.commit();
    }


    public  List<String> getMealName(){
        Gson gson = new Gson();
        List<String> productFromShared = new ArrayList<>();
        String jsonPreferences = mSharedPreferences.getString(TYPE, "");
        Type type = new TypeToken<List<String>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);
        return productFromShared;
    }



}
