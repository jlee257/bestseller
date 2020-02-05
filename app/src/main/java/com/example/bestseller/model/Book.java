package com.example.bestseller.model;

import androidx.annotation.NonNull;

public class Book {

    public final String isbn;
    public final String title;
    public final String image;
    public final String author;
    public final String description;

    public Book(String isbn, String title, String image, String author, String description) {
        this.isbn = isbn;
        this.title = title;
        this.image = image;
        this.author = author;
        this.description = description;
    }

    public boolean contains(@NonNull String text) {
        String lowerText = text.toLowerCase();
        return isbn.toLowerCase().contains(lowerText)
                || title.toLowerCase().contains(lowerText)
                || author.toLowerCase().contains(lowerText)
                || description.toLowerCase().contains(lowerText);
    }
}
