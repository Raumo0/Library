package com.netcracker.library;

import com.netcracker.library.controllers.LibrarianController;
import com.netcracker.library.entities.Librarian;
import com.netcracker.library.views.LibrarianView;

/**
 * Created by raumo0 on 13.10.16.
 */
public class RunLibrary {
    Librarian librarian = new Librarian("Steve",
            "Stevens","s@protonmail.com",1);
    LibrarianView librarianView = new LibrarianView(librarian);
    LibrarianController a = new LibrarianController(librarian, librarianView);
}
