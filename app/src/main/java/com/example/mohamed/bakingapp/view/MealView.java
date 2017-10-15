package com.example.mohamed.bakingapp.view;

import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.MealStep;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface MealView {
    void showStepClickedMessage(MealStep mealStep);
    void showIngredients(List<MealIngredients> ingredientses);
    void showMeal(Meal meal);
    void showConnectionError();
}
