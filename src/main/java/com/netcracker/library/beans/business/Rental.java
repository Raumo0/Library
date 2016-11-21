package com.netcracker.library.beans.business;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.BookIssue;

import java.util.Date;

/**
 * Created by raumo0 on 27.10.16.
 */
public class Rental extends Entity {
    private static final long serialVersionUID = 1L;
    private String comment;
    private Date rentalDate;
    private Date returnDate;
    private BookState stateBefore;
    private BookState stateAfter;
    private User staff_user;
    private User user;
    private BookIssue bookIssue;
    private Book book;

    public Rental() {
    }

    public Rental(Rental rental){
        super(rental);
        this.comment = rental.getComment();
        this.rentalDate = rental.getRentalDate();
        this.returnDate = rental.getReturnDate();
        this.stateBefore = rental.getStateBefore();
        this.stateAfter = rental.getStateAfter();
        this.staff_user = rental.getStaff_user();
        this.user = rental.getUser();
        this.bookIssue = rental.getBookIssue();
        this.book = rental.getBook();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookState getStateBefore() {
        return stateBefore;
    }

    public void setStateBefore(BookState stateBefore) {
        this.stateBefore = stateBefore;
    }

    public BookState getStateAfter() {
        return stateAfter;
    }

    public void setStateAfter(BookState stateAfter) {
        this.stateAfter = stateAfter;
    }

    public User getStaff_user() {
        return staff_user;
    }

    public void setStaff_user(User staff_user) {
        this.staff_user = staff_user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookIssue getBookIssue() {
        return bookIssue;
    }

    public void setBookIssue(BookIssue bookIssue) {
        this.bookIssue = bookIssue;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rental rental = (Rental) o;

        if (comment != null ? !comment.equals(rental.comment) : rental.comment != null) return false;
        if (rentalDate != null ? !rentalDate.equals(rental.rentalDate) : rental.rentalDate != null) return false;
        if (returnDate != null ? !returnDate.equals(rental.returnDate) : rental.returnDate != null) return false;
        if (stateBefore != rental.stateBefore) return false;
        if (stateAfter != rental.stateAfter) return false;
        if (staff_user != null ? !staff_user.equals(rental.staff_user) : rental.staff_user != null) return false;
        if (user != null ? !user.equals(rental.user) : rental.user != null) return false;
        if (bookIssue != rental.bookIssue) return false;
        return book != null ? book.equals(rental.book) : rental.book == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rentalDate != null ? rentalDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (stateBefore != null ? stateBefore.hashCode() : 0);
        result = 31 * result + (stateAfter != null ? stateAfter.hashCode() : 0);
        result = 31 * result + (staff_user != null ? staff_user.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (bookIssue != null ? bookIssue.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "comment='" + comment + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", stateBefore=" + stateBefore +
                ", stateAfter=" + stateAfter +
                ", staff_user=" + staff_user +
                ", user=" + user +
                ", bookIssue=" + bookIssue +
                ", book=" + book +
                "} " + super.toString();
    }
}
