package com.dan.mealapp.view.detail;

import com.dan.mealapp.model.Meals;


public interface DetailView {
    //TODO 62. Add void method  for showLoading, hideLoading, setMeal, onErrorLoading;
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
