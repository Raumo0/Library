package com.netcracker.library.entities;

import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.Country;

import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Author extends Person {
    private LinkedList<BookEdition> bookEditions;
    private String bio;
    private BookCategory bookCategory;

    public Author(String firstName, String lastName, String email, long id) {
        super(firstName, lastName, email, id);
    }

    public Author(String firstName, String lastName, String email, long id, GregorianCalendar birthday,
                  Country country, LinkedList<BookEdition> bookEditions, String bio, BookCategory bookCategory) {
        super(firstName, lastName, email, id, birthday, country);
        this.bookEditions = bookEditions;
        this.bio = bio;
        this.bookCategory = bookCategory;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LinkedList<BookEdition> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(LinkedList<BookEdition> bookEditions) {
        this.bookEditions = bookEditions;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
