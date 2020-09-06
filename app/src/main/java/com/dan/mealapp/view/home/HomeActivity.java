/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dan.mealapp.R;
import com.dan.mealapp.Utils;
import com.dan.mealapp.adapter.RecyclerViewHomeAdapter;
import com.dan.mealapp.adapter.ViewPagerHeaderAdapter;
import com.dan.mealapp.databinding.ActivityMainBinding;
import com.dan.mealapp.model.Categories;
import com.dan.mealapp.model.Meals;
import com.dan.mealapp.view.category.CategoryActivity;
import com.dan.mealapp.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// TODO 31 implement the HomeView interface at the end
public class HomeActivity extends AppCompatActivity implements HomeView
{
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    /*
     * TODO 32 Add @BindView Annotation (1)
     *  or use dataBinding for this :
     * 1. viewPagerHeader
     * 2. recyclerCategory
     */
    ActivityMainBinding binding;

    // TODO 33 Create variable for presenter;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //if use dataBinding :
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /* if use ButterKnife :
         *  TODO 34 bind the ButterKnife (2): ButterKnife.bind(this);
         */


        // TODO 35 Declare the presenter
        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    // TODO 36 Overriding the interface
    @Override
    public void showLoading()
    {
        binding.shimmerMeal.setVisibility(View.VISIBLE);
        binding.shimmerCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        binding.shimmerMeal.setVisibility(View.GONE);
        binding.shimmerCategory.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal)
    {
        /* get meal list :
         * for (Meals.Meal mealResult : meal)
         * {
         *     Log.i("LOG_TAG", "Meal name ==>" + mealResult.getStrMeal());
         * }
         */
        //Create variable for ViewPagerHeaderAdapter and declare it
        //note that im using androidx.viewpager.widget.ViewPager not ViewPager2
        ViewPagerHeaderAdapter headerAdapter=new ViewPagerHeaderAdapter(meal);
        //set viewPager adapter to headerAdapter:
        binding.viewPagerHeader.setAdapter(headerAdapter);
        headerAdapter.notifyDataSetChanged();

        //set viewPager click listener

        headerAdapter.setOnItemClickListener((v, position) -> {
            //TODO 66. make an intent to DetailActivity (get the name of the meal from the edit text view, then send the name of the meal to DetailActivity)
            //TextView mealName=v.findViewById(R.id.mealName);
            Intent intent=new Intent(HomeActivity.this, DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL,meal.get(position).getIdMeal());
            startActivity(intent);
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category)
    {
        /*get category list:
         *for (Categories.Category category1 : category)
         *{
         *    Log.i("LOG_TAG", "Category name ==>" + category1.getStrCategory());
         *}
         */
        RecyclerViewHomeAdapter homeAdapter=new RecyclerViewHomeAdapter(category);
        binding.recyclerCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false);
        binding.recyclerCategory.setLayoutManager(layoutManager);
        binding.recyclerCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
        //set recyclerView click listener
        homeAdapter.setOnItemClickListener(new RecyclerViewHomeAdapter.ClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {
                //TODO 45. Create an Intent and add extra data (put to intent)
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
                intent.putExtra(EXTRA_POSITION, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message)
    {
        Utils.showDialogMessage(this,"ERROR",message);
    }


}
