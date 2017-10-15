package com.example.mohamed.bakingapp.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.Meal;
import com.example.mohamed.bakingapp.model.MealIngredients;
import com.example.mohamed.bakingapp.model.MealStep;

import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealStepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Meal meal;
    private Context mContext;
    private MealStepItemListener mealStepItemListener;

    public MealStepAdapter(Context mContext,MealStepItemListener mealStepItemListener){
        this.mContext=mContext;
        this.mealStepItemListener=mealStepItemListener;
    }

    public void replaceMeal(Meal meal){
        this.meal=meal;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.step_item_view,parent,false);
        if (viewType==TYPE_HEADER){
            return new HeaderHolder(view);
        }else if (viewType==TYPE_ITEM){
            return new StepHolder(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
   if (holder instanceof HeaderHolder){
      HeaderHolder headerHolder= (HeaderHolder) holder;
       headerHolder.bind(meal.getIngredients());
   }else if (holder instanceof StepHolder){
     StepHolder stepHolder= (StepHolder) holder;
       stepHolder.bind(meal.getSteps().get(position-1));
   }

    }
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }
    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }


    @Override
    public int getItemCount() {

        try {
            return meal.getSteps().size()+1;
        }catch (Exception e){
            return 1;
        }
    }

 class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
     private TextView textView;
     private List<MealIngredients>  mealIngredientses;
     public HeaderHolder(View itemView) {
         super(itemView);
         itemView.setOnClickListener(this);
         textView= (TextView) itemView.findViewById(R.id.count_step);

     }

     public void bind(List<MealIngredients> ingredientses){
         mealIngredientses=ingredientses;

         textView.setText(String.valueOf(ingredientses.size())+"  ingredientses");
     }
     @Override
     public void onClick(View v) {
        mealStepItemListener.onMealingiClick(mealIngredientses);
     }
 }

 class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
     private TextView stepnum;
     private TextView stepDes;
     private MealStep meals;
     public StepHolder(View itemView) {
         super(itemView);
         itemView.setOnClickListener(this);
         stepnum= (TextView) itemView.findViewById(R.id.count_step);
         stepDes= (TextView) itemView.findViewById(R.id.step_des);
     }

     public void bind(MealStep meal){
         this.meals=meal;
         stepnum.setText("Step :"+String.valueOf(meal.getId()));
         stepDes.setText(meal.getShortDescription());
     }
     @Override
     public void onClick(View v) {
       mealStepItemListener.onMealstepClick(meals,getAdapterPosition());
     }
 }

    public interface MealStepItemListener {
        void onMealstepClick(MealStep item,int p);
        void onMealingiClick(List<MealIngredients> ingredientses);
    }
}
