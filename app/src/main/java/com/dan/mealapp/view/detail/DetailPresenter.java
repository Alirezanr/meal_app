/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 4/7/19 7:07 PM                                               -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.view.detail;


import android.util.Log;

import com.dan.mealapp.Utils;
import com.dan.mealapp.model.Meals;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter
{
    private DetailView view;

    public DetailPresenter(DetailView view)
    {
        this.view = view;
    }

    void getMealById(String mealId)
    {
        //TODO 63. Call the void show loading before starting to make a request to the server
        view.showLoading();
        //TODO 64. Make a request to the server (Don't forget to hide loading when the response is received)
        Call<Meals> mealCall = Utils.getApi().getMealById(mealId);
        mealCall.enqueue(new Callback<Meals>()
        {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response)
            {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null)
                {
                    //TODO 65. Set response (meal)
                    view.setMeal(response.body().getMeals().get(0));
                  //  Log.i(Utils.TAG, "ashao" + );
                }
                else
                {
                    view.onErrorLoading(response.message());
                    Log.i(Utils.TAG, "DetailPresenter/onResponse : response is not successful or is null :\n" + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t)
            {
                view.hideLoading();
                Log.i(Utils.TAG, "DetailPresenter/onFailure : :\n" + t.getLocalizedMessage());
            }
        });
      /*  Call<Meals.Meal> mealCall=Utils.getApi().getMealById(mealId);
        mealCall.enqueue(new Callback<Meals.Meal>()
        {
            @Override
            public void onResponse(@NonNull Call<Meals.Meal> call, @NonNull Response<Meals.Meal> response)
            {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null)
                {
                    //TODO 65. Set response (meal)
                  //  view.setMeal(response.body());
                    Log.i(Utils.TAG, "a"+response.body().getStrMeal());
                }
                else
                {
                    view.onErrorLoading(response.message());
                    Log.i(Utils.TAG, "DetailPresenter/onResponse : response is not successful or is null :\n" + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals.Meal> call, @NonNull Throwable t)
            {
                view.hideLoading();
                Log.i(Utils.TAG, "DetailPresenter/onFailure : :\n" + t.getLocalizedMessage());
            }
        });

        Utils.getApi().getMealById(mealId).enqueue(new Callback<Meals.Meal>()
        {
            @Override
            public void onResponse(@NonNull Call<Meals.Meal> call, @NonNull Response<Meals.Meal> response)
            {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null)
                {
                    //TODO 65. Set response (meal)
                    view.setMeal(response.body());
                }
                else
                {
                    view.onErrorLoading(response.message());
                    Log.i(Utils.TAG, "DetailPresenter/onResponse : response is not successful or is null :\n" + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals.Meal> call, @NonNull Throwable t)
            {
                view.hideLoading();
                Log.i(Utils.TAG, "DetailPresenter/onFailure : :\n" + t.getLocalizedMessage());
            }
        });*/
    }
}
