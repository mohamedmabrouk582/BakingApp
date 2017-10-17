package com.example.mohamed.bakingapp.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohamed.bakingapp.Adpter.MealAdapter;
import com.example.mohamed.bakingapp.Api.Requests;
import com.example.mohamed.bakingapp.ContentProvider.DBOPeration.Dboperation;
import com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce.DataManager;
import com.example.mohamed.bakingapp.Applcation.MyApp;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.presenter.main.MainViewPresenter;
import com.example.mohamed.bakingapp.ui.MealStepsActivity;
import com.example.mohamed.bakingapp.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealListFragment extends Fragment implements MainView,MealAdapter.MealItemListener {
    private RecyclerView mRecyclerView;
    private View view;
    private DataManager dataManager;
    private MealAdapter mealAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainViewPresenter mainViewPresenter;
    private String SAVED_RECYCLER_VIEW_STATUS_ID="id";
    private int lastFirstVisiblePosition=0;
    private String STEPS="step";
    private List<Meal> meals=new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.meal_list_fragment,container,false);
         mainViewPresenter=new MainViewPresenter(this,new Requests(getActivity()));
        dataManager=((MyApp) getActivity().getApplicationContext()).getDataManager();
        intiRecyler();
        initSwipeRefreshLayout();
        return view;
    }

    private void intiRecyler(){
        mealAdapter=new MealAdapter(getActivity(),this);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.meals_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        if (width>height){
             mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),numberOfColumns()));
        }else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setAdapter(mealAdapter);
    }
    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int widthDivider =120;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    private void insert(List<Meal> meals){
        List<String> list=new ArrayList<>();
        this.meals=meals;
        Dboperation.getInstance(getActivity()).delete();
        for (Meal meal:meals) {
            list.add(meal.getName());
            for (MealIngredients ingredients:meal.getIngredients()){
                Dboperation.getInstance(getActivity()).insertMeal(ingredients,meal.getName());
            }
        }
        dataManager.clear();
        dataManager.saveMealName(list);
    }
    void initSwipeRefreshLayout(){

        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainViewPresenter.loadMealData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mainViewPresenter.loadMealData();
    }

    @Override
    public void showProgress() {
     if (!mSwipeRefreshLayout.isRefreshing()){
         mSwipeRefreshLayout.post(new Runnable() {
             @Override
             public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
             }
         });
     }
    }

    @Override
    public void hideProgress() {
     if (mSwipeRefreshLayout.isRefreshing()){
         mSwipeRefreshLayout.setRefreshing(false);
     }
    }

    @Override
    public void showMealClickedMessage(Meal meal) {
      startActivity(MealStepsActivity.newIntent(getActivity(),meal));
    }

    @Override
    public void showMeals(List<Meal> meals) {
      mealAdapter.replaceData(meals);
        insert(meals);
        dataManager.clear();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), "Error at Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieItemClick(Meal item) {
     mainViewPresenter.clickMealItem(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(SAVED_RECYCLER_VIEW_STATUS_ID, listState);
        outState.putParcelableArrayList(STEPS, (ArrayList<? extends Parcelable>) meals);

        //
        lastFirstVisiblePosition =((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        (mRecyclerView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);

        super.onViewStateRestored(savedInstanceState);
    }

}
