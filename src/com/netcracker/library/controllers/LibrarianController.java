package com.netcracker.library.controllers;

import com.netcracker.library.Book;
import com.netcracker.library.models.LibrarianModel;
import com.netcracker.library.views.LibrarianView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by raumo0 on 14.10.16.
 */
public class LibrarianController implements ActionListener {
    private LibrarianModel librarianModel;
    private LibrarianView librarianView;

    public LibrarianController(LibrarianModel librarianModel, LibrarianView librarianView) {
        this.librarianModel = librarianModel;
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
