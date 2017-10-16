package com.example.mohamed.bakingapp.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.ui.MealListActivity;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link AppWidgetConfigureActivity AppWidgetConfigureActivity}
 */
public class AppWidget extends AppWidgetProvider {
    public static String name;
    public static String EXTRA_STRING="string";
    public static String ACTION_TOAST="toast";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_TOAST)) {
            Intent intent1=new Intent(context, MealListActivity.class);
            context.startActivity(intent1);
        }
        super.onReceive(context, intent);
    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String widgetText = AppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
         name=widgetText;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.mealName, widgetText);
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(appWidgetId, R.id.widgetCollectionList, intent);

        final Intent onItemClick = new Intent(context, AppWidget.class);
        onItemClick.setAction(ACTION_TOAST);
        onItemClick.setData(Uri.parse(onItemClick
                .toUri(Intent.URI_INTENT_SCHEME)));
        final PendingIntent onClickPendingIntent = PendingIntent
                .getBroadcast(context, 0, onItemClick,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widgetCollectionList,
                onClickPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            AppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

