package com.dan.mealapp.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dan.mealapp.R;
import com.dan.mealapp.Utils;
import com.dan.mealapp.adapter.RecyclerViewMealByCategory;
import com.dan.mealapp.databinding.FragmentCategoryBinding;
import com.dan.mealapp.model.Meals;
import com.dan.mealapp.view.detail.DetailActivity;
import com.dan.mealapp.view.home.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

public class CategoryFragment extends Fragment implements CategoryView
{
    AlertDialog.Builder descDialog;

    // // TODO: 40. define dataBinding
    FragmentCategoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // TODO: 41. Bind DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);


        // TODO 58. Declare clickListener for cardCategory
        binding.cardCategory.setOnClickListener(view -> {
            descDialog.setPositiveButton("CLOSE", (dialogInterface, i) -> {
                // dialogInterface.dismiss();
                descDialog.show();
            });
        });


        //TODO 52. getArguments with KEY
        if (getArguments() != null)
        {
            //TODO 53. set Value from argument data to view
            binding.textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(binding.imageCategory);
            Picasso.get()
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(binding.imageCategoryBg);

            // TODO 53-2. declare  AlertDialog
            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage("EXTRA_DATA_DESC");

            //TODO 57. declare presenter
            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }


        // TODO: 42.return binding.getRoot();
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


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
    public void setMeals(List<Meals.Meal> meals)
    {

        //TODO 56. set RecyclerViewMealByCategory adapter;
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(meals);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerView.setClipToPadding(false);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener((view, position) -> {

            TextView mealId = view.findViewById(R.id.mealIdHolder);
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(HomeActivity.EXTRA_DETAIL, mealId.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message)
    {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

}
