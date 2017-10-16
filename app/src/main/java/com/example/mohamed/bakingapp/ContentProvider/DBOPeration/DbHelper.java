package com.example.mohamed.bakingapp.ContentProvider.DBOPeration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohamed.bakingapp.ContentProvider.DbShema.Shema;
import com.example.mohamed.bakingapp.ContentProvider.DbShema.Shema.TableIngredients;

/**
 * Created by mohamed on 14/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DBNAME="meal.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TableIngredients.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                TableIngredients.COLS.MEAL+" , "+
                TableIngredients.COLS.NAME + " TEXT, " +
                TableIngredients.COLS.QUANTITY +" , "+
                TableIngredients.COLS.MEASURE +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE" + TableIngredients.NAME);
        onCreate(db);
    }

}
