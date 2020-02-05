package com.example.bestseller.ui.search.reactive2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;

import com.example.bestseller.R;
import com.example.bestseller.ui.search.BaseSearchActivity;
import com.example.bestseller.ui.search.reactive3.SearchViewModel3;

public class ReactiveSearchActivity2 extends BaseSearchActivity {

    private SearchViewModel2 viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SearchViewModel2.class);

        viewModel.getBooks().observe(this, books -> {
            getBookAdapter().submitList(books);
        });

        viewModel.isLoading().observe( this, isLoading -> {
            Log.e("SearchActivity", "visibility=" + isLoading);
            setProgressVisibility(isLoading);
        });
    }


    @Override
    protected void onQueryChange(String query) {
        viewModel.submitQuery(query);
    }
}
