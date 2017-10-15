package com.example.mohamed.bakingapp.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.bakingapp.Adpter.IngredientsAdapter;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.presenter.ingredient.IngredientsViewPresenter;
import com.example.mohamed.bakingapp.view.IngredientView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 05/10/2017.
 */

public class IngredientsFragment extends Fragment implements IngredientView {
    private RecyclerView mRecyclerView;
    private View view;
    private IngredientsViewPresenter ingredientsViewPresenter;
    private static String MEAL_KEY="MEAL_KEY";
    private List<MealIngredients> mealIngredients;
    private IngredientsAdapter mAdapter;

    public static IngredientsFragment newFragment(List<MealIngredients> ingredientses){
        Bundle bundle=new Bundle();

        bundle.putParcelableArrayList(MEAL_KEY, (ArrayList<? extends Parcelable>) ingredientses);
        IngredientsFragment fragment=new IngredientsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.meal_steps_list_view,container,false);
        mealIngredients=getArguments().getParcelableArrayList(MEAL_KEY);
        ingredientsViewPresenter=new IngredientsViewPresenter(this,mealIngredients);
        initReaycler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ingredientsViewPresenter.LoadIngredientsData();
    }

    private void initReaycler(){
        mAdapter=new IngredientsAdapter(getActivity());
        mRecyclerView= (RecyclerView) view.findViewById(R.id.meals_steps_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showIngredientItem(List<MealIngredients> mealIngredient) {
        mAdapter.replaceData(mealIngredient);
    }
}
