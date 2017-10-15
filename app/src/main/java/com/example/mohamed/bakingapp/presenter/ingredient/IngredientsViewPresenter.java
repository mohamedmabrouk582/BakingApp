package com.example.mohamed.bakingapp.presenter.ingredient;

import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.presenter.BasePresenter;
import com.example.mohamed.bakingapp.presenter.ingredient.IngredientPresenter;
import com.example.mohamed.bakingapp.view.IngredientView;

import java.util.List;

/**
 * Created by mohamed on 05/10/2017.
 */

public class IngredientsViewPresenter extends BasePresenter implements IngredientPresenter {

    private IngredientView view;
    private List<MealIngredients> mealIngredients;

    public IngredientsViewPresenter(IngredientView view,List<MealIngredients> mealIngredients){
        this.view=view;
        this.mealIngredients=mealIngredients;
    }
    @Override
    public void LoadIngredientsData() {
        view.showIngredientItem(mealIngredients);
    }
}
