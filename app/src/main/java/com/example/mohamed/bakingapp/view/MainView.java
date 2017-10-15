package com.example.mohamed.bakingapp.view;

import com.example.mohamed.bakingapp.model.Meal;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface MainView  {
    void showProgress();
    void hideProgress();
    void showMealClickedMessage(Meal meal);
    void showMeals(List<Meal> meals);
    void showConnectionError();
}
