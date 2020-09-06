/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 4/7/19 7:33 PM                                               -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.view.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dan.mealapp.R;
import com.dan.mealapp.Utils;
import com.dan.mealapp.databinding.ActivityDetailBinding;
import com.dan.mealapp.model.Meals;
import com.dan.mealapp.view.home.HomeActivity;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;


public class DetailActivity extends AppCompatActivity implements DetailView
{
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        setupActionBar();

        //TODO 67. Get data from the intent
        Intent intent = getIntent();
        String mealId = intent.getStringExtra(HomeActivity.EXTRA_DETAIL);


        //TODO 68. Declare the presenter (put the name of the meal name from the data intent to the presenter)
        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealById(mealId);
    }

    private void setupActionBar()
    {
        setSupportActionBar(binding.toolbar);
        binding.collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        binding.collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        binding.collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        binding.collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor)
    {
        binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((binding.collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(binding.collapsingToolbarLayout)))
            {
                if (binding.toolbar.getNavigationIcon() != null)
                    binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            }
            else
            {
                if (binding.toolbar.getNavigationIcon() != null)
                    binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading()
    {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(Meals.Meal meal)
    {
        if (meal != null)
        {
            Picasso.get().load(meal.getStrMealThumb()).into(binding.mealThumb);
            binding.collapsingToolbarLayout.setTitle(meal.getStrMeal());
            binding.category.setText(meal.getStrCategory());
            binding.country.setText(meal.getStrArea());
            binding.instructions.setText(meal.getStrInstructions());
            setupActionBar();

            //===

            if (!meal.getStrIngredient1().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient1());
            }
            if (!meal.getStrIngredient2().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient2());
            }
            if (!meal.getStrIngredient3().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient3());
            }
            if (!meal.getStrIngredient4().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient4());
            }
            if (!meal.getStrIngredient5().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient5());
            }
            if (!meal.getStrIngredient6().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient6());
            }
            if (!meal.getStrIngredient7().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient7());
            }
            if (!meal.getStrIngredient8().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient8());
            }
            if (!meal.getStrIngredient9().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient9());
            }
            if (!meal.getStrIngredient10().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient10());
            }
            if (!meal.getStrIngredient11().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient11());
            }
            if (!meal.getStrIngredient12().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient12());
            }
            if (!meal.getStrIngredient13().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient13());
            }
            if (!meal.getStrIngredient14().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient14());
            }
            if (!meal.getStrIngredient15().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient15());
            }
            if (!meal.getStrIngredient16().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient16());
            }
            if (!meal.getStrIngredient17().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient17());
            }
            if (!meal.getStrIngredient18().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient18());
            }
            if (!meal.getStrIngredient19().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient19());
            }
            if (!meal.getStrIngredient20().isEmpty())
            {
                binding.ingredients.append("\n \u2022 " + meal.getStrIngredient20());
            }

            if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure1());
            }
            if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure2());
            }
            if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure3());
            }
            if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure4());
            }
            if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure5());
            }
            if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure6());
            }
            if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure7());
            }
            if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure8());
            }
            if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure9());
            }
            if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure10());
            }
            if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure11());
            }
            if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure12());
            }
            if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure13());
            }
            if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure14());
            }
            if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure15());
            }
            if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure16());
            }
            if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure17());
            }
            if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure18());
            }
            if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure19());
            }
            if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0)))
            {
                binding.measures.append("\n : " + meal.getStrMeasure20());
            }


            binding.youtube.setOnClickListener(v -> {
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                intentYoutube.setData(Uri.parse(meal.getStrYoutube()));
                startActivity(intentYoutube);
            });

            binding.source.setOnClickListener(v -> {
                Intent intentSource = new Intent(Intent.ACTION_VIEW);
                intentSource.setData(Uri.parse(meal.getStrSource()));
                startActivity(intentSource);
            });
        }
        else
        {
            Log.i(Utils.TAG, "setMeal:Empty");
        }
    }

    @Override
    public void onErrorLoading(String message)
    {
        Utils.showDialogMessage(this, "Error", message);
    }
}
