/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/24/19 12:55 PM                                             -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.dan.mealapp.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dan.mealapp.R;
import com.dan.mealapp.adapter.ViewPagerCategoryAdapter;
import com.dan.mealapp.databinding.ActivityCategoryBinding;
import com.dan.mealapp.model.Categories;
import com.dan.mealapp.view.home.HomeActivity;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class CategoryActivity extends AppCompatActivity
{

    //TODO 46. define DataBinding in xml layout and java class
    ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //TODO 47. Bind DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);

        initActionBar();

        //TODO 48. Init getIntent() data from home activity
        initIntent();
        //TODO 51. Declare fragment viewPager adapter
    }

    private void initIntent()
    {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = (int) intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);
        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();
    }

    private void initActionBar()
    {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
