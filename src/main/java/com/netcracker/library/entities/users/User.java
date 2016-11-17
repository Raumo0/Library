package com.netcracker.library.entities.users;

import com.netcracker.library.entities.Person;

/**
 * Created by raumo0 on 15.11.16.
 */
public class User extends Person {
    private static final long serialVersionUID = 1L;

    public User(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
    }
}
