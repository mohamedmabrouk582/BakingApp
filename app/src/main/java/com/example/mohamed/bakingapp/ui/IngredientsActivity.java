package com.example.mohamed.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.example.mohamed.bakingapp.fragments.IngredientsFragment;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.utils.NetworkChangeReceiver;
import com.example.mohamed.bakingapp.utils.SingleFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 05/10/2017.
 */

public class IngredientsActivity extends SingleFragmentActivity {
    private static String INGREDIENT="INGREDIENT";

    public static Intent newIntent(Context context, List<MealIngredients> ingredientses){
        Intent  intent=new Intent(context,IngredientsActivity.class);
        intent.putParcelableArrayListExtra(INGREDIENT, (ArrayList<? extends Parcelable>) ingredientses);
        return intent;
    }


    @Override
    public Fragment CreateFragment() {

        return IngredientsFragment.newFragment(getIntent().<MealIngredients>getParcelableArrayListExtra(INGREDIENT));

    }
}
