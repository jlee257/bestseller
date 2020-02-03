package com.example.bestseller.ui.search.impl;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.bestseller.R;
import com.example.bestseller.model.Book;
import com.example.bestseller.model.BookSource;
import com.example.bestseller.ui.search.BaseSearchActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseSearchActivity {

    private ProgressBar progressBar;
    private List<Book> masterBookList = new ArrayList<>();
    private boolean isList1Ready = false;
    private boolean isList2Ready = false;
    private String currentQuery = "";

    private Handler queryDelayHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        progressBar = findViewById(R.id.progressBar);


        queryDelayHandler = new Handler();
        queryDelayHandler.


        BookSource.getBestNonFictions(books -> {
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
            progressBar.setVisibility(View.GONE);
        }
    }



    private void applyFilter() {
        List<Book> displayList = new ArrayList<>();

        for (Book book : masterBookList) {
            if (book.contains(currentQuery)) {
                displayList.add(book);
            }
        }

        getBookAdapter().submitList(displayList);
    }






}
