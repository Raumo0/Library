package com.netcracker.library;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Author extends Person {
    private String bio;

    public Author(String fName, String sName, String email, long id) {
        super(fName, sName, email, id);
    }

    public Author(String fName, String sName, String email, long id, String bio) {
        super(fName, sName, email, id);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
