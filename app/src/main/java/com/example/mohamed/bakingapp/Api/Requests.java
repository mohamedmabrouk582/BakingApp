package com.example.mohamed.bakingapp.Api;

import android.content.Context;

import com.example.mohamed.bakingapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by mohamed on 04/10/2017.
 */

public class Requests  {
    static ApiInterface apiInterface;
    private Context mContext;
    public Requests(Context context){
        mContext=context;
    }

    public  void GetMeals(final RequestsInterface requestsInterface){
     apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<List<Meal>> call=apiInterface.getListMeal();

        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                requestsInterface.onSucess(response.body());
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
              requestsInterface.onFaile(t.getMessage());
            }
        });

    }
}
