package com.netcracker.library;

import com.netcracker.library.menu.LibraryView;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Main {
    public static void main(String[] args) {
//        ExampleRunDAO exampleRunDao = new ExampleRunDAO();
//        exampleRunDao.runDao();
//        RunLibrary mainRunLibrary = new RunLibrary();
        LibraryView cp = new LibraryView("Cp1251");
        cp.execute();
    }
}
