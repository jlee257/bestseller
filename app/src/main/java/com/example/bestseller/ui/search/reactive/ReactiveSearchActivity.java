package com.example.bestseller.ui.search.reactive;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;

import com.example.bestseller.R;
import com.example.bestseller.ui.search.BaseSearchActivity;

public class ReactiveSearchActivity extends BaseSearchActivity {

    private SearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        viewModel.getBooks().observe(this, books -> {
            getBookAdapter().submitList(books);
        });

        viewModel.isLoading().observe( this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
    }


    @Override
    protected void onQueryChange(String query) {
        viewModel.submitQuery(query);
    }
}
