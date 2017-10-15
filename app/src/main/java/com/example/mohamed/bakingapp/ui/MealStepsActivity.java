package com.example.mohamed.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.fragments.IngredientsFragment;
import com.example.mohamed.bakingapp.fragments.MealStepFragment;
import com.example.mohamed.bakingapp.fragments.MealStepListFragment;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.utils.SingleFragmentActivity;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealStepsActivity extends SingleFragmentActivity implements MealStepListFragment.CallBack {
    private static String MEAL_KEY="MEAL_KEY";
    private static Meal meals;
    public static Intent newIntent(Context context, Meal meal){
        Intent intent=new Intent(context,MealStepsActivity.class);
        meals=meal;
        return intent;
    }
    @Override
    public Fragment CreateFragment() {
        return MealStepListFragment.newFragment(meals);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (findViewById(R.id.detail_fragment_container)!=null) {

            try{
                onCrimeSelected(meals,0,0);
            }catch (Exception e){

            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Meal meal,int type,int p) {
        if (findViewById(R.id.detail_fragment_container)==null){

            startActivity(StepsPagerActivity.newIntent(this,meal,p));

        }else {
            Fragment newDetail = null;
            switch (type){
                case 0:
                    newDetail=IngredientsFragment.newFragment(meal.getIngredients());
                    break;
                case 1:
                    newDetail= MealStepFragment.newFragment(meal.getSteps().get(p-1));
                    break;
            }
try {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.detail_fragment_container, newDetail)
            .commit();
}catch (Exception e){

}

        }
    }
}
