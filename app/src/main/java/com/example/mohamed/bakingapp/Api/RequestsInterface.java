package com.example.mohamed.bakingapp.Api;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public interface RequestsInterface {
   <T> void onSucess(List<T> meals);
    void onFaile(String f);
}
