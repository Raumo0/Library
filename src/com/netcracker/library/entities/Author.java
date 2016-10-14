package com.netcracker.library.entities;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Author extends Person {
    private String bio;

    public Author(String firstName, String lastName, String email, long id) {
        super(firstName, lastName, email, id);
    }

    public Author(String firstName, String lastName, String email, long id, String bio) {
        super(firstName, lastName, email, id);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
