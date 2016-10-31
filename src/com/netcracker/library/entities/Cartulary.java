package com.netcracker.library.entities;

import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.IssueBook;

import java.io.Serializable;
import java.util.Date;

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
    private Date receiveDate;
    private Date returnDate;
    private IssueBook issueBook;

    public Cartulary(long id, Book book, Reader reader, BookState before, Date receiveDate) {
        this.book = book;
        this.reader = reader;
        this.before = before;
        this.id = id;
        this.receiveDate = receiveDate;
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

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public IssueBook getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(IssueBook issueBook) {
        this.issueBook = issueBook;
    }
}
