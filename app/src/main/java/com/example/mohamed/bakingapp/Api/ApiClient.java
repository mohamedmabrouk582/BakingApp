package com.example.mohamed.bakingapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed on 04/10/2017.
 */

public class ApiClient {
    private static Retrofit retrofit = null;
    private static String BASE_URL="https://d17h27t6h515a5.cloudfront.net";
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
