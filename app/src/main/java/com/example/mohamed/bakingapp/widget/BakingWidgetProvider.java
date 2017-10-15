package com.example.mohamed.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.mohamed.bakingapp.Api.Requests;
import com.example.mohamed.bakingapp.Applcation.MyApp;
import com.example.mohamed.bakingapp.ContentProvider.DBOPeration.Dboperation;
import com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce.DataManager;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.MealIngredients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 10/10/2017.
 */

public class BakingWidgetProvider implements RemoteViewsService.RemoteViewsFactory{
   List<MealIngredients> mCollection = new ArrayList<>();

    public static String mealName;
    Context mContext = null;
    public BakingWidgetProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.v("dataaa",mCollection.size()+"");
        return mCollection.size();
    }


    @Override
    public RemoteViews getViewAt(int position) {
        DataManager dataManager=((MyApp) mContext.getApplicationContext()).getDataManager();
        MealIngredients mItem = mCollection.get(position);
        Log.d("meals",mItem.toString());
        String grdiant =mItem.getQuantity() + "            " +mItem.getIngredient() ;
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(),R.layout.baking_app_widget);
        remoteView.setTextViewText(R.id.name, grdiant);
        remoteView.setTextColor(R.id.name, Color.WHITE);
        Intent intent = new Intent();
        remoteView.setOnClickFillInIntent(R.id.Fragment_Container, intent);
        remoteView.setTextViewText(R.id.item_name,dataManager.getMealName());
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
       mCollection.clear();
        /*  for ibsetr Data in collection */
       mCollection= Dboperation.getInstance(mContext).getMeals();
    }

}
