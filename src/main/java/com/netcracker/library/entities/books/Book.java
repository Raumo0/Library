package com.netcracker.library.entities.books;

import com.netcracker.library.entities.Entity;
import com.netcracker.library.entities.business.Rental;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private BookEdition bookEdition;
    private BookState bookState;
    private BookPosition bookPosition;
    private LinkedList<Rental> cartularies;

    public Book(int id, BookEdition bookEdition, BookState bookState, BookPosition bookPosition) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
        this.cartularies = new LinkedList<>();
    }

    public Book(int id, BookEdition bookEdition, BookState bookState, BookPosition bookPosition,
                LinkedList<Rental> cartularies) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
        this.cartularies = cartularies;
    }

    public LinkedList<Rental> getCartularies() {
        return cartularies;
    }

    public void setCartularies(LinkedList<Rental> cartularies) {
        this.cartularies = cartularies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BookPosition getBookPosition() {
        return bookPosition;
    }

    public void setBookPosition(BookPosition bookPosition) {
        this.bookPosition = bookPosition;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!bookEdition.equals(book.bookEdition)) return false;
        if (bookState != book.bookState) return false;
        if (bookPosition != book.bookPosition) return false;
        return cartularies.equals(book.cartularies);

    }

    @Override
    public int hashCode() {
        int result = (id ^ (id >>> 32));
        result = 31 * result + bookEdition.hashCode();
        result = 31 * result + bookState.hashCode();
        result = 31 * result + bookPosition.hashCode();
        result = 31 * result + cartularies.hashCode();
        return result;
    }
}
