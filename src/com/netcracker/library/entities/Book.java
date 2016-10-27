package com.netcracker.library.entities;

import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book implements Serializable {
    private long id;
    private BookEdition bookEdition;
//    private Person lastOwner;
    private BookState bookState;
    private BookPosition bookPosition;
    private LinkedList<Cartulary> cartularies;

    public Book(long id, BookEdition bookEdition, BookState bookState, BookPosition bookPosition) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
        this.cartularies = new LinkedList<>();
    }

    public Book(long id, BookEdition bookEdition, BookState bookState, BookPosition bookPosition,
                LinkedList<Cartulary> cartularies) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
        this.cartularies = cartularies;
    }

    public LinkedList<Cartulary> getCartularies() {
        return cartularies;
    }

    public void setCartularies(LinkedList<Cartulary> cartularies) {
        this.cartularies = cartularies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
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

    public boolean inStore(){
        if (bookPosition == BookPosition.IN_STORE)
            return true;
        return false;
    }

    public boolean inReadingRoom(){
        if (bookPosition == BookPosition.IN_READING_ROOM)
            return true;
        return false;
    }

    //RENAME it
    public boolean inReader(){
        if (bookPosition == BookPosition.IN_READER)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookEdition=" + bookEdition +
                ", bookState=" + bookState +
                ", bookPosition=" + bookPosition +
                ", cartularies=" + cartularies +
                '}';
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
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + bookEdition.hashCode();
        result = 31 * result + bookState.hashCode();
        result = 31 * result + bookPosition.hashCode();
        result = 31 * result + cartularies.hashCode();
        return result;
    }
}
