/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/24/19 5:13 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.view.category;


import android.util.Log;

import com.dan.mealapp.R;
import com.dan.mealapp.Utils;
import com.dan.mealapp.model.Meals;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter
{
    private CategoryView view;

    public CategoryPresenter(CategoryView view)
    {
        this.view = view;
    }

    void getMealByCategory(String category)
    {
        // TODO 55. Make request meals by category
        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealsByCategory(category);
        mealsCall.enqueue(new Callback<Meals>()
        {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response)
            {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null)
                {
                    view.setMeals(response.body().getMeals());
                }
                else
                {
                    view.onErrorLoading(response.message());
                    Log.i(Utils.TAG, "CategoryPresenter/mealsCall/onResponse: response is null : \n"+response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t)
            {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
                Log.i(Utils.TAG, "CategoryPresenter/mealsCall/onFailure: response failed : \n"+t.toString());

            }
        });
    }
}
