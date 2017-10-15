package com.example.mohamed.bakingapp.model;

/**
 * Created by mohamed on 10/10/2017.
 */

public class MealsLab {
    private static final MealsLab ourInstance = new MealsLab();

    public static MealsLab getInstance() {
        return ourInstance;
    }

    private MealsLab() {
    }
}
