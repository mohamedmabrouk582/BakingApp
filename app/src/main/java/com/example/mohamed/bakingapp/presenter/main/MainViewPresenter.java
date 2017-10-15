package com.example.mohamed.bakingapp.presenter.main;

import com.example.mohamed.bakingapp.Api.Requests;
import com.example.mohamed.bakingapp.Api.RequestsInterface;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.presenter.BasePresenter;
import com.example.mohamed.bakingapp.view.MainView;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MainViewPresenter extends BasePresenter implements MainPresenter {
    private MainView mainView;
    private Requests mRequests;

    public MainViewPresenter(MainView  mainView,Requests mRequests){
        this.mRequests=mRequests;
        this.mainView=mainView;
    }

    @Override
    public void loadMealData() {
        mainView.showProgress();
        mRequests.GetMeals(new RequestsInterface() {
            @Override
            public <T> void onSucess(List<T> meals) {
                mainView.hideProgress();
                mainView.showMeals((List<Meal>) meals);
            }

            @Override
            public void onFaile(String f) {
                mainView.showConnectionError();
                mainView.hideProgress();
            }
        });
    }

    @Override
    public void clickMealItem(Meal item) {
     mainView.showMealClickedMessage(item);
    }
}
