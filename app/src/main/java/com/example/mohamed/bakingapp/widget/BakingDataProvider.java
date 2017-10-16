package com.example.mohamed.bakingapp.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mohamed.bakingapp.ContentProvider.DBOPeration.Dboperation;
import com.example.mohamed.bakingapp.model.mealInge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 16/10/2017.
 */

@SuppressLint("NewApi")
public class BakingDataProvider implements RemoteViewsService.RemoteViewsFactory {
    List<mealInge> mCollections = new ArrayList();
    private Context mContext;

    public BakingDataProvider(Context context, Intent intent){
        mContext=context;
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
        return mCollections.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        String s= mCollections.get(position).getQuantity()+"  "+ mCollections.get(position).getIngredient()+"   "+mCollections.get(position).getMeasure();
        mView.setTextViewText(android.R.id.text1,s);
        mView.setTextColor(android.R.id.text1, Color.BLACK);
        final Intent fillInIntent = new Intent();
        fillInIntent.setAction(AppWidget.ACTION_TOAST);
        mView.setOnClickFillInIntent(android.R.id.text1, fillInIntent);
        return mView;
    }

    private void initData() {
        mCollections.clear();
       mCollections= Dboperation.getInstance(mContext).getMeals(AppWidget.name);
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
}
