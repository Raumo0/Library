package com.netcracker.library.entities;

import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Reader extends Person {
    private LinkedList<Book> books;

    public Reader(String firstName, String lastName, String email, long id) {
        super(firstName, lastName, email, id);
    }

    public Book SearchBook(){
        //TODO
        return null;
    }

    public void ReturnBook(Book book){

    }

    public Book GetBook(){
        //TODO
        return null;
    }
}
