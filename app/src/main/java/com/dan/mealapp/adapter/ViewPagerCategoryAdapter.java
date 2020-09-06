package com.dan.mealapp.adapter;

import android.os.Bundle;

import com.dan.mealapp.R;
import com.dan.mealapp.model.Categories;
import com.dan.mealapp.view.category.CategoryFragment;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter
{

    private List<Categories.Category> categories;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<Categories.Category> categories)
    {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int i)
    {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        //TODO 50. Add arguments to fragment
        args.putString("EXTRA_DATA_NAME",categories.get(i).getStrCategory());
        args.putString("EXTRA_DATA_DESC",categories.get(i).getStrCategoryDescription());
        args.putString("EXTRA_DATA_IMAGE",categories.get(i).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount()
    {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return categories.get(position).getStrCategory();
    }
}
