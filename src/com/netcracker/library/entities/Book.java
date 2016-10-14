package com.netcracker.library.entities;

import com.netcracker.library.enums.Category;

import java.util.List;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book {
    private String title;
    private Author author;
    private String description;
    private String isbn;
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
