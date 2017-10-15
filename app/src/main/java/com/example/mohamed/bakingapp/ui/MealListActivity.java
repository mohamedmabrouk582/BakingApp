package com.example.mohamed.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.mohamed.bakingapp.fragments.IngredientsFragment;
import com.example.mohamed.bakingapp.fragments.MealListFragment;
import com.example.mohamed.bakingapp.utils.SingleFragmentActivity;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,MealListActivity.class);
        return intent;
    }
    @Override
    public Fragment CreateFragment() {
        return new MealListFragment();
    }
}
