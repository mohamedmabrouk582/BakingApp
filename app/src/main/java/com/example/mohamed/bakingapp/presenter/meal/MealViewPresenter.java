package com.example.mohamed.bakingapp.presenter.meal;

import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.MealStep;
import com.example.mohamed.bakingapp.presenter.BasePresenter;
import com.example.mohamed.bakingapp.presenter.meal.MealPresenter;
import com.example.mohamed.bakingapp.view.MealView;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealViewPresenter extends BasePresenter implements MealPresenter {
    private MealView mealView;
    private Meal meal;

    public MealViewPresenter(MealView mealView,Meal meal){
        this.meal=meal;
        this.mealView=mealView;
    }
    @Override
    public void loadMealData() {
         mealView.showMeal(meal);
    }

    @Override
    public void clickStepItem(MealStep item) {
        mealView.showStepClickedMessage(item);

    }

    @Override
    public void clickMealIngredinets(List<MealIngredients> ingredientses) {
     mealView.showIngredients(ingredientses);
    }



}
