/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.api;


import com.dan.mealapp.model.Categories;
import com.dan.mealapp.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("search.php?f=d")
    Call<Meals> getMeal();


    // TODO 12 also make the Call like getMeals() method for category
    @GET("categories.php")
    Call<Categories> getCategories();

    // TODO 54. add interface get meals by category
    @GET("filter.php")
    Call<Meals> getMealsByCategory(@Query("c") String category);

    //TODO 61. Call the search.php with query string the meal name @GET("search.php)
    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String mealId);
}
