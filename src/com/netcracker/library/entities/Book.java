package com.netcracker.library.entities;

import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book {
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
}
