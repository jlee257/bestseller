package com.example.bestseller.model;

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

    public boolean contains(String text) {
        return isbn.contains(text)
                || title.contains(text)
                || author.contains(text)
                || description.contains(text);
    }
}
