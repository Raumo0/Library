package com.netcracker.library.entities;

import java.util.LinkedList;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Reader extends Person {
    private LinkedList<Cartulary> cartularies;
    private String login;
    private String password;

    public Reader(String firstName, String lastName, String email, long id, String login, String password) {
        super(firstName, lastName, email, id);
        this.login = login;
        this.password = password;
    }

    public Book SearchBook(){
        //TODO
        return null;
    }

    public void ReturnBook(Book book){

    }

    public Book GetBook(){
        //TODO
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "cartularies=" + cartularies +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
