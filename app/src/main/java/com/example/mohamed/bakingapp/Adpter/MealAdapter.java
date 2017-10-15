package com.example.mohamed.bakingapp.Adpter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 04/10/2017.
 */

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealHolder> {
    private Context mContext;
    private MealItemListener mealItemListener;
    private List<Meal> meals=new ArrayList<>();
    public MealAdapter(Context mContext,MealItemListener mealItemListener){
        this.mContext=mContext;
        this.mealItemListener=mealItemListener;
    }

    @Override
    public MealHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.meal_item_view,parent,false);
        return new MealHolder(view);
    }

    public void replaceData(List<Meal> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MealHolder holder, int position) {
     holder.Bind(meals.get(position));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }



    public class MealHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mealName;
        private ImageView imageView;
        private TextView serving;
        public MealHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.meal_image);
            itemView.setOnClickListener(this);
            mealName= (TextView) itemView.findViewById(R.id.meal_name);
            serving= (TextView) itemView.findViewById(R.id.servings);
        }

        public void Bind(Meal meal){
            mealName.setText(meal.getName());
            Picasso.with(mContext).load(Uri.parse(meal.getImage()))
                    .placeholder(R.drawable.meal).error(R.drawable.meal).into(imageView);
               serving.setText(meal.getServings()+"");
        }

        @Override
        public void onClick(View v) {
           mealItemListener.onMovieItemClick(meals.get(getAdapterPosition()));
        }
    }

    public interface MealItemListener {
        void onMovieItemClick(Meal item);
    }
}
