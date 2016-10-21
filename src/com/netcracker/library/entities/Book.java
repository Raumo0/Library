package com.netcracker.library.entities;

import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.io.Serializable;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book implements Serializable {
    private long id;
    private BookEdition bookEdition;
    private Person lastOwner;
    private BookState bookState;
    private BookPosition bookPosition;

    public Book(long id, BookEdition bookEdition, BookState bookState, BookPosition bookPosition) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
    }

    public Book(long id, BookEdition bookEdition, Person lastOwner, BookState bookState, BookPosition bookPosition) {
        this.id = id;
        this.bookEdition = bookEdition;
        this.lastOwner = lastOwner;
        this.bookState = bookState;
        this.bookPosition = bookPosition;
    }

    public long getId() {
        return id;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public Person getLastOwner() {
        return lastOwner;
    }

    public void setLastOwner(Person lastOwner) {
        this.lastOwner = lastOwner;
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
                ", lastOwner=" + lastOwner +
                ", bookState=" + bookState +
                ", bookPosition=" + bookPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (bookEdition != null ? !bookEdition.equals(book.bookEdition) : book.bookEdition != null) return false;
        if (!lastOwner.equals(book.lastOwner)) return false;
        if (bookState != book.bookState) return false;
        return bookPosition == book.bookPosition;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (bookEdition != null ? bookEdition.hashCode() : 0);
        result = 31 * result + lastOwner.hashCode();
        result = 31 * result + (bookState != null ? bookState.hashCode() : 0);
        result = 31 * result + (bookPosition != null ? bookPosition.hashCode() : 0);
        return result;
    }
}
