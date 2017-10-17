package com.example.mohamed.bakingapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohamed.bakingapp.Adpter.MealStepAdapter;
import com.example.mohamed.bakingapp.ContentProvider.sherdPrefarnce.DataManager;
import com.example.mohamed.bakingapp.Applcation.MyApp;
import com.example.mohamed.bakingapp.ContentProvider.DBOPeration.Dboperation;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.MealStep;
import com.example.mohamed.bakingapp.presenter.meal.MealViewPresenter;
import com.example.mohamed.bakingapp.view.MealView;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealStepListFragment extends Fragment  implements MealView,MealStepAdapter.MealStepItemListener{
    private static final String STEPS ="steps" ;
    private static final String SAVED_RECYCLER_VIEW_STATUS_ID ="id" ;
    private RecyclerView mRecyclerView;
    private View  view;
    private MealStepAdapter  mealStepAdapter;
    private MealViewPresenter mealViewPresenter;
    private static Meal meal;
    private CallBack mCallBack;
    private DataManager dataManager;
    private int lastFirstVisiblePosition=0;

    public interface CallBack{
        void onCrimeSelected(Meal meal,int type,int p);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack= (CallBack) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack=null;
    }

    public static MealStepListFragment newFragment(Meal meals){
//    Bundle bundle=new Bundle();
//        bundle.putParcelable(MEAL_KEY,meal);
//        MealStepListFragment fragment=new MealStepListFragment();
//        fragment.setArguments(bundle);
//        return fragment;
        meal=meals;
        return new MealStepListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        dataManager=((MyApp) getActivity().getApplication()).getDataManager();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.meal_steps_list_view,container,false);
      //  meal= (Meal) getArguments().getParcelable(MEAL_KEY);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(0xFFFFFFFF);
        //set toolbar appearance
       // toolbar.setBackground(R.color.material_blue_grey_800);

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle(meal.getName());
        mealViewPresenter=new MealViewPresenter(this,meal);
       // Toast.makeText(getActivity(), meal.getSteps().size()+"", Toast.LENGTH_SHORT).show();
        initRecycler();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        mealViewPresenter.loadMealData();
    }

    private void initRecycler(){
        mealStepAdapter=new MealStepAdapter(getActivity(),this);
       // mealStepAdapter.replaceMeal(meal);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.meals_steps_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mealStepAdapter);

    }

    @Override
    public void showStepClickedMessage(MealStep mealStep) {
       mealViewPresenter.clickStepItem(mealStep);
    }

    @Override
    public void showIngredients(List<MealIngredients> ingredientses) {
       mealViewPresenter.clickMealIngredinets(ingredientses);
    }

    @Override
    public void showMeal(Meal meal) {
         mealStepAdapter.replaceMeal(meal);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), "Error at Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealstepClick(MealStep item,int p) {
        mCallBack.onCrimeSelected(meal,1,p);
    }

    @Override
    public void onMealingiClick(List<MealIngredients> ingredientses) {
       // Toast.makeText(getActivity(), ingredientses.size()+"", Toast.LENGTH_SHORT).show();
       // startActivity(IngredientsActivity.newIntent(getActivity(),ingredientses));
      mCallBack.onCrimeSelected(meal,0,0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(SAVED_RECYCLER_VIEW_STATUS_ID, listState);
        outState.putParcelableArrayList(STEPS,meal.getSteps());

        lastFirstVisiblePosition =((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();



        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        (mRecyclerView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);

        super.onViewStateRestored(savedInstanceState);
    }


}
