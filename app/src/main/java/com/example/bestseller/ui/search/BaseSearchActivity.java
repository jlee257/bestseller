package com.example.bestseller.ui.search;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestseller.R;

public abstract class BaseSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setSupportActionBar(findViewById(R.id.toolbar));

        bookAdapter = new BookAdapter();
        RecyclerView booksView = findViewById(R.id.booksView);
        booksView.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new BookQueryTextListener());

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        searchView.setOnQueryTextListener(null);
    }


    protected BookAdapter getBookAdapter() {
        return bookAdapter;
    }

    @MainThread
    protected void onQueryChange(String query) {
        // Override this
    }


    private class BookQueryTextListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            onQueryChange(newText);
            return true;
        }
    }

}
