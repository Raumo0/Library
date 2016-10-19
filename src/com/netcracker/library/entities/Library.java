package com.netcracker.library.entities;

import java.util.ArrayList;

/**
 * Created by raumo0 on 19.10.16.
 */
public class Library {
    private ArrayList<Author> authors;
    private ArrayList<Reader> readers;
    private ArrayList<Librarian> librarians;
    private ArrayList<BookEdition> bookEditions;
    private ArrayList<Book> books;

    // Private constructor prevents instantiation from other classes
    private Library() {}

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class LibraryHolder {
        private static final Library INSTANCE = new Library();
    }

    public static Library getInstance() {
        return LibraryHolder.INSTANCE;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
        this.readers = readers;
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }

    public ArrayList<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(ArrayList<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
