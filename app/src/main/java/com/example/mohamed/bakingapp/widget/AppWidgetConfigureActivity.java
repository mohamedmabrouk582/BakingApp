package com.example.mohamed.bakingapp.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.bakingapp.Api.Requests;
import com.example.mohamed.bakingapp.Api.RequestsInterface;
import com.example.mohamed.bakingapp.Applcation.MyApp;
import com.example.mohamed.bakingapp.ContentProvider.DBOPeration.Dboperation;
import com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce.DataManager;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.view.StepView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The configuration screen for the {@link AppWidget AppWidget} AppWidget.
 */
public class AppWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "com.example.mohamed.bakingapp.widget.AppWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private RecyclerView mRecyclerView;
    private DataManager dataManager;
    public AppWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.app_widget_configure);
        initRecycl();
        //findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

      //  mAppWidgetText.setText(loadTitlePref(AppWidgetConfigureActivity.this, mAppWidgetId));
    }

    private void initRecycl(){
        dataManager=((MyApp) getApplicationContext()).getDataManager();
        mRecyclerView= (RecyclerView) findViewById(R.id.widget_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String>list=Dboperation.getInstance(this).getMealName();
        WidgetAdapter adapter=new WidgetAdapter(this,list);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class WidgetHolder extends RecyclerView.ViewHolder{
         private TextView mTextView;
        private String name;
        public WidgetHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = AppWidgetConfigureActivity.this;

                    // When the button is clicked, store the string locally
                    String widgetText = name;
                    saveTitlePref(context, mAppWidgetId, widgetText);

                    // It is the responsibility of the configuration activity to update the app widget
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    AppWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

                    // Make sure we pass back the original appWidgetId
                    Intent resultValue = new Intent();
                    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                    setResult(RESULT_OK, resultValue);
                    finish();
                }
            });
        }

        public void bind(String names){
            name=names;
            mTextView.setText(name);
        }
    }

    class WidgetAdapter extends RecyclerView.Adapter<WidgetHolder>{
        private Context mContext;
        private List<String> list=new ArrayList<>();
        public WidgetAdapter(Context context,List<String> list){
            mContext=context;
            this.list=list;
        }


        @Override
        public WidgetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new WidgetHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_activated_1,parent,false));
        }

        @Override
        public void onBindViewHolder(WidgetHolder holder, int position) {
         holder.bind(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

