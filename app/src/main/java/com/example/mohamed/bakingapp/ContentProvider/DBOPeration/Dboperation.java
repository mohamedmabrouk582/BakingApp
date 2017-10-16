package com.example.mohamed.bakingapp.ContentProvider.DBOPeration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mohamed.bakingapp.ContentProvider.DbShema.Shema;
import com.example.mohamed.bakingapp.ContentProvider.DbShema.Shema.TableIngredients;
import com.example.mohamed.bakingapp.ContentProvider.MealProvider;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.mealInge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mohamed on 14/10/2017.
 */

public class Dboperation {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static  Dboperation ourInstance;

    public static Dboperation getInstance(Context context) {
        if (ourInstance==null){
            ourInstance=new Dboperation(context);
        }
        return ourInstance;
    }

    private Dboperation(Context context) {
        mContext=context.getApplicationContext();
        mDatabase=new DbHelper(mContext).getWritableDatabase();
    }

    public String insertMeal(MealIngredients meal,String mealname){
        return mDatabase.insert(TableIngredients.NAME,null,getValues(meal,mealname))>0?
                "done" :"not";
     // mContext.getContentResolver().insert(MealProvider.CONTENT_URI,getValues(meal,mealname));
    }
    public void delete(){
        mContext.getContentResolver().delete(MealProvider.CONTENT_URI,null,null);
    }

    public List<String> getMealName(){
       Cursor cursor= mContext.getContentResolver().query(MealProvider.CONTENT_URI,new String[]{TableIngredients.COLS.MEAL},null,null,null);
        Set<String> list=new HashSet<>();
        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()){
                 list.add(cursor.getString(cursor.getColumnIndex(TableIngredients.COLS.MEAL)));
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        List<String> list1=new ArrayList<>();
        for (String s:list) {
         list1.add(s);
        }
        return list1;
    }

    public List<mealInge> getMeals(String meal){
      //  MealWrapper wrapper=new MealWrapper(mContext.getContentResolver().query(MealProvider.CONTENT_URI,null,TableIngredients.COLS.MEAL+" =? ",new String[]{meal},null));
        MealWrapper wrapper=quary(TableIngredients.NAME,TableIngredients.COLS.MEAL+" =? ",new String[]{meal});
        List<mealInge> list=new ArrayList<>();
        wrapper.moveToFirst();
        try {
            while (!wrapper.isAfterLast()){
                list.add(wrapper.getMealIngredients());
                wrapper.moveToNext();
            }
        }finally {
            wrapper.close();
        }

    return list;
    }

    private MealWrapper quary(String Name,String whereClause, String[] whereArgs){
        Cursor cursor=mDatabase.query(
                Name,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MealWrapper(cursor);
    }

    private ContentValues getValues(MealIngredients meal,String mealName){
        ContentValues values=new ContentValues();
        values.put(TableIngredients.COLS.MEAL,mealName);
        values.put(TableIngredients.COLS.NAME,meal.getIngredient());
        values.put(TableIngredients.COLS.QUANTITY,meal.getQuantity());
        values.put(TableIngredients.COLS.MEASURE,meal.getMeasure());
        return values;
    }

}
