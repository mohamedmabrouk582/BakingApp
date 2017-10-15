package com.example.mohamed.bakingapp.presenter.main;

import com.example.mohamed.bakingapp.model.Meal;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface MainPresenter {
    void loadMealData();
    void clickMealItem(Meal item);
}
