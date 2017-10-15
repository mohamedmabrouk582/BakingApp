package com.example.mohamed.bakingapp.utils;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.ui.MealListActivity;

/**
 * Created by mohamed on 04/10/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private NetworkChangeReceiver networkChangeReceiver;
    public abstract Fragment CreateFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

         networkChangeReceiver=new NetworkChangeReceiver();
        networkChangeReceiver.onReceive(this, MealListActivity.newIntent(this));
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(networkChangeReceiver,intentFilter);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.Fragment_Container);

        if (fragment==null){
            fragment=CreateFragment();

            fragmentManager.beginTransaction().replace(R.id.Fragment_Container,fragment).commit();
        }
    }

    @Override
    protected void onStop()
    {
//        unregisterReceiver(networkChangeReceiver);
        super.onStop();
    }
}
