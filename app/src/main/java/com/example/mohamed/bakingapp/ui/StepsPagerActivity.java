package com.example.mohamed.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.fragments.IngredientsFragment;
import com.example.mohamed.bakingapp.fragments.MealStepFragment;
import com.example.mohamed.bakingapp.model.Meal;

/**
 * Created by mohamed on 08/10/2017.
 */

public class StepsPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private static Meal meal;
    int po=0;
    public static Intent newIntent(Context context,Meal meals,int po){
        meal=meals;
        Intent intent=new Intent(context,StepsPagerActivity.class);
        intent.putExtra("postion",po);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals_view_pager);
      //  Toolbar  toolbar= (Toolbar) findViewById(R.id.toolbars);
        po=getIntent().getIntExtra("postion",0);
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (po==0){
            activity.getSupportActionBar().setTitle(meal.getIngredients().size()+" Ingredients");
        }else {
            activity.getSupportActionBar().setTitle(meal.getSteps().get(po-1).getShortDescription());
        }
        mViewPager= (ViewPager) findViewById(R.id.steps_view_pager);

        FragmentManager fragmentManager=getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if (position==0){
                  return   IngredientsFragment.newFragment(meal.getIngredients());
                }else {
                   return MealStepFragment.newFragment(meal.getSteps().get(position-1));
                }
            }

            @Override
            public int getCount() {
                return meal.getSteps().size()+1;
            }
        });
        for (int i = 1; i < meal.getSteps().size()+1; i++) {
                if (meal.getSteps().get(i-1).getId()+1 == po) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
        }
    }
}
