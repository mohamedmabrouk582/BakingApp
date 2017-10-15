package com.example.mohamed.bakingapp.Api;

import com.example.mohamed.bakingapp.model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface ApiInterface {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Meal>> getListMeal();
}
