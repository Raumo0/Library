package com.netcracker.library.beans.books;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book extends Entity {
    private static final long serialVersionUID = 1L;
    private BookPosition bookPosition;
    private BookState bookState;
    private BookEdition bookEdition;
    private Collection<Rental> rentals;

    public Book() {}

    public Book(Book book) {
        super(book);
        this.bookPosition = book.getBookPosition();
        this.bookEdition = book.getBookEdition();
        this.bookState = book.getBookState();
        this.rentals = book.getRentals();
    }

    public BookPosition getBookPosition() {
        return bookPosition;
    }

    public void setBookPosition(BookPosition bookPosition) {
        this.bookPosition = bookPosition;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public Collection<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Collection<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (bookPosition != book.bookPosition) return false;
        return bookState == book.bookState;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bookPosition != null ? bookPosition.hashCode() : 0);
        result = 31 * result + (bookState != null ? bookState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookPosition=" + bookPosition +
                ", bookState=" + bookState +
                "} " + super.toString();
    }
}
