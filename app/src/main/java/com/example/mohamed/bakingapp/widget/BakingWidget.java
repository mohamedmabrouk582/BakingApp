package com.example.mohamed.bakingapp.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce.DataManager;
import com.example.mohamed.bakingapp.Applcation.MyApp;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.ui.MealListActivity;

import java.util.Random;

/**
 * Created by mohamed on 10/10/2017.
 */

public class BakingWidget extends AppWidgetProvider {
    private static  DataManager dataManager;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        dataManager=((MyApp) context.getApplicationContext()).getDataManager();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setRemoteAdapter(R.id.list, new Intent(context, WidgetService.class));
        views.setPendingIntentTemplate(R.id.list, PendingIntent.getActivity(context, 0, new Intent(context, MealListActivity.class), 0));
        views.setTextViewText(R.id.item_name,dataManager.getMealName());
        views.setTextColor(R.id.item_name, Color.WHITE);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context,appWidgetManager,appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, WidgetService.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.list);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.list,
                new Intent(context, WidgetService.class));
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(0, R.id.list,
                new Intent(context, WidgetService.class));
    }
}
