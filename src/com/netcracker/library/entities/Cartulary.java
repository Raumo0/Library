package com.netcracker.library.entities;

import com.netcracker.library.enums.BookState;

import java.io.Serializable;

/**
 * Created by raumo0 on 27.10.16.
 */
public class Cartulary implements Serializable {
    private long id;
    private Book book;
    private Reader reader;
    private String comment;
    private BookState before;
    private BookState after;

    public Cartulary(long id, Book book, Reader reader, BookState before) {
        this.book = book;
        this.reader = reader;
        this.before = before;
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookState getBefore() {
        return before;
    }

    public void setBefore(BookState before) {
        this.before = before;
    }

    public BookState getAfter() {
        return after;
    }

    public void setAfter(BookState after) {
        this.after = after;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
