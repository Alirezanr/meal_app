
package com.dan.mealapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dan.mealapp.R;
import com.dan.mealapp.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder>
{

    private List<Meals.Meal> meals;
    private static ClickListener clickListener;

    public RecyclerViewMealByCategory(List<Meals.Meal> meals)
    {
        this.meals = meals;
    }

    @NonNull
    @Override
    public RecyclerViewMealByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_meal,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMealByCategory.RecyclerViewHolder viewHolder, int i)
    {
        String strMealThumb = meals.get(i).getStrMealThumb() + "/preview";
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);
        viewHolder.mealName.setText(meals.get(i).getStrMeal());
        viewHolder.mealIdHolder.setText(meals.get(i).getIdMeal());
    }


    @Override
    public int getItemCount()
    {
        return meals.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView mealThumb;
        TextView mealName;
        TextView mealIdHolder;

        RecyclerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealIdHolder = itemView.findViewById(R.id.mealIdHolder);
            mealThumb = itemView.findViewById(R.id.mealThumb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener)
    {
        RecyclerViewMealByCategory.clickListener = clickListener;
    }


    public interface ClickListener
    {
        void onClick(View view, int position);
    }
}
