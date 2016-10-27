package com.netcracker.library.entities;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Librarian extends Person {

    public Librarian(String firstName, String lastName, String email, long id) {
        super(firstName, lastName, email, id);
    }

    public void IssueMembershipCard(){
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

    public Book IssueBookInReadingRoom(){
        //TODO
        return null;
    }

    public Book IssueBookOnMembershipCard(){
        //TODO
        return null;
    }

    public void AddBook(Book book){
        //TODO
    }

    public void RemoveBook(Book book){
        //TODO
    }

    public void UpdateBook(Book book){
        //TODO
    }

    @Override
    public String toString() {
        return "Librarian{} " + super.toString();
    }
}
