package com.netcracker.library;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book {
    private String title;
    private Author author;
    private String description;
    private String isbn;

    public Book(String title, Author author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String title, Author author, String description, String isbn) throws Exception {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
