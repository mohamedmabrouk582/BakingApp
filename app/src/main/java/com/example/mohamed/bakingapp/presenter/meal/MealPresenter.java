package com.example.mohamed.bakingapp.presenter.meal;

import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.MealStep;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface MealPresenter {
    void loadMealData();
    void clickStepItem(MealStep item);
    void clickMealIngredinets(List<MealIngredients> ingredientses);
}
