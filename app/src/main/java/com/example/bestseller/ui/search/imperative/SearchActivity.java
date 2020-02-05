package com.example.bestseller.ui.search.imperative;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.WorkerThread;

import com.example.bestseller.R;
import com.example.bestseller.data.BookSource;
import com.example.bestseller.model.Book;
import com.example.bestseller.ui.search.BaseSearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchActivity extends BaseSearchActivity {

    private ProgressBar progressBar;
    private List<Book> masterBookList = new ArrayList<>();
    private boolean isList1Ready = false;
    private boolean isList2Ready = false;
    private String currentQuery = "";


    private Executor workerThreadExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = findViewById(R.id.progressBar);
        workerThreadExecutor = Executors.newCachedThreadPool();

        BookSource.getBestFictions(books -> {
            isList1Ready = true;
            addList(books);
        });

        BookSource.getBestNonFictions(books -> {
            isList2Ready = true;
            addList(books);
        });
    }



    private synchronized void addList(List<Book> books) {
        masterBookList.addAll(books);
        if (isList1Ready && isList2Ready) {
            applyFilter();
        }
    }


    @WorkerThread
    private void applyFilter() {
        List<Book> displayList = new ArrayList<>();

        for (Book book : masterBookList) {
            if (book.contains(currentQuery)) {
                displayList.add(book);
            }
        }

        runOnUiThread(() -> {
            progressBar.setVisibility(View.GONE);
            getBookAdapter().submitList(displayList);
        });
    }


    @Override
    protected void onQueryChange(String query) {
        currentQuery = query;

        if (isList1Ready && isList2Ready) {
            workerThreadExecutor.execute(this::applyFilter);
        }
    }
}
