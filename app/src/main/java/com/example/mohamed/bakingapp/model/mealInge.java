package com.example.mohamed.bakingapp.model;

/**
 * Created by mohamed on 16/10/2017.
 */

public class mealInge {
    private float quantity;
    private String measure;
    private String ingredient;
    private String mealName;

    public mealInge(float quantity, String measure, String ingredient, String mealName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
        this.mealName = mealName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
