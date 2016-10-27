package com.netcracker.library.service;

import com.netcracker.library.entities.Librarian;

import java.util.Collection;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface LibrarianService extends PersonService {
    long insertLibrarian(Librarian librarian);

    Collection getLibrarians();
}
