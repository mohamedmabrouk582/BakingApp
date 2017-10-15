package com.example.mohamed.bakingapp.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.MealIngredients;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 05/10/2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {

        private List<MealIngredients> list=new ArrayList<>();
    private Context mContext;
    public IngredientsAdapter(Context mContext){
        this.mContext=mContext;
    }

    public void replaceData(List<MealIngredients> ingredientses){
        list=ingredientses;
        notifyDataSetChanged();
    }

    @Override
    public IngredientsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(mContext).inflate(R.layout.step_item_view,parent,false);
        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsHolder holder, int position) {
      holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class IngredientsHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private TextView textView;
        public IngredientsHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.count_step);
            textView= (TextView) itemView.findViewById(R.id.step_des);

        }

        public void bind(MealIngredients mealIngredients){
            mTextView.setText(mealIngredients.getQuantity()+" "+mealIngredients.getMeasure());
            textView.setText(mealIngredients.getIngredient());
        }

    }
}
