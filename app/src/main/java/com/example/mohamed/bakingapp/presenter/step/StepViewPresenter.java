package com.example.mohamed.bakingapp.presenter.step;

import com.example.mohamed.bakingapp.model.MealStep;
import com.example.mohamed.bakingapp.presenter.BasePresenter;
import com.example.mohamed.bakingapp.presenter.step.StepPresenter;
import com.example.mohamed.bakingapp.view.StepView;

/**
 * Created by mohamed on 05/10/2017.
 */

public class StepViewPresenter extends BasePresenter implements StepPresenter {
    private StepView stepView;
    private MealStep step;

    public StepViewPresenter(StepView stepView,MealStep step){
        this.step=step;
        this.stepView=stepView;
    }
    @Override
    public void LoadStepData() {
        stepView.showStep(step);
    }
}
