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

import java.util.ArrayList;
import java.util.List;

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

    public void insertMeal(MealIngredients meal){
        mContext.getContentResolver().insert(MealProvider.CONTENT_URI,getValues(meal));
    }
    public void delete(){
        mContext.getContentResolver().delete(MealProvider.CONTENT_URI,null,null);
    }

    public List<MealIngredients> getMeals(){
        MealWrapper wrapper=new MealWrapper(mContext.getContentResolver().query(MealProvider.CONTENT_URI,null,null,null,null));
        List<MealIngredients> list=new ArrayList<>();
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

    private ContentValues getValues(MealIngredients meal){
        ContentValues values=new ContentValues();
        values.put(TableIngredients.COLS.NAME,meal.getIngredient());
        values.put(TableIngredients.COLS.QUANTITY,meal.getQuantity());
        values.put(TableIngredients.COLS.MEASURE,meal.getMeasure());
        return values;
    }

}
