package com.example.bestseller.ui.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestseller.R;
import com.example.bestseller.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final List<Book> books =  new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < books.size()) {
            holder.bindTo(books.get(position));
        } else {
            holder.clear();
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public void submitList(@NonNull List<Book> books) {
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView titleView, authorView, descriptionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleView = itemView.findViewById(R.id.titleView);
            authorView = itemView.findViewById(R.id.authorView);
            descriptionView = itemView.findViewById(R.id.descriptionView);
        }

        void bindTo(@NonNull Book book) {
            titleView.setText(book.title);
            authorView.setText(book.author);
            descriptionView.setText(book.description);
        }

        void clear() {
            titleView.setText("");
            authorView.setText("");
            descriptionView.setText("");
        }
    }
}
