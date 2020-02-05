package com.example.bestseller.ui.search.reactive3;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;

import com.example.bestseller.R;
import com.example.bestseller.ui.search.BaseSearchActivity;

public class ReactiveSearchActivity3 extends BaseSearchActivity {

    private SearchViewModel3 viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SearchViewModel3.class);

        viewModel.getBooks().observe(this, books -> {
            getBookAdapter().submitList(books);
        });

        viewModel.isLoading().observe( this, isLoading -> {
            setProgressVisibility(isLoading);
        });
    }


    @Override
    protected void onQueryChange(String query) {
        viewModel.submitQuery(query);
    }
}
