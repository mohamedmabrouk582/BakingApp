package com.example.mohamed.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by mohamed on 16/10/2017.
 */

public class WidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
       BakingDataProvider provider=new BakingDataProvider(getApplicationContext(),intent);
        return provider;
    }
}
