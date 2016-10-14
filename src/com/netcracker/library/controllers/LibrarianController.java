package com.netcracker.library.controllers;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Librarian;
import com.netcracker.library.views.LibrarianView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by raumo0 on 14.10.16.
 */
public class LibrarianController implements ActionListener {
    private Librarian librarian;
    private LibrarianView librarianView;

    public LibrarianController(Librarian librarian, LibrarianView librarianView) {
        this.librarian = librarian;
        this.librarianView = librarianView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: action
    }

    public void IssueMembershopCard(){
        //TODO
    }

    public void CancelMembership(){
        //TODO
    }

    public void UpdateReaderDetails(long id, String email){
        //TODO
    }

    public void ReturnBook(Book book){

    }
}
