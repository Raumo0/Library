package com.netcracker.library;

import com.netcracker.library.controllers.LibrarianController;
import com.netcracker.library.models.LibrarianModel;
import com.netcracker.library.views.LibrarianView;

/**
 * Created by raumo0 on 13.10.16.
 */
public class RunLibrary {
    LibrarianModel librarianModel = new LibrarianModel("Steve",
            "Stevens","s@protonmail.com",1);
    LibrarianView librarianView = new LibrarianView(librarianModel);
    LibrarianController a = new LibrarianController(librarianModel, librarianView);
}
