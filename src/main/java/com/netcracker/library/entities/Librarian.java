package com.netcracker.library.entities;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Librarian extends Person {

    public Librarian(String firstName, String lastName, String email, Integer id) {
        super(firstName, lastName, email, id);
    }

    @Override
    public String toString() {
        return "Librarian{id=" + super.getId() + '}';
    }
}
