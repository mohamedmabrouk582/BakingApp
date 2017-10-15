package com.example.mohamed.bakingapp.ContentProvider.DBOPeration;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mohamed.bakingapp.ContentProvider.DbShema.Shema;
import com.example.mohamed.bakingapp.model.MealIngredients;

/**
 * Created by mohamed on 14/10/2017.
 */

public class MealWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public MealWrapper(Cursor cursor) {
        super(cursor);
    }

    public MealIngredients getMealIngredients(){
        String name=getString(getColumnIndex(Shema.TableIngredients.COLS.NAME));
        float quantity=getFloat(getColumnIndex(Shema.TableIngredients.COLS.QUANTITY));
        String measure=getString(getColumnIndex(Shema.TableIngredients.COLS.MEASURE));
        MealIngredients mealIngredients=new MealIngredients(quantity,name,measure);
        return mealIngredients;
    }
}
