package com.netcracker.library.entities.books;

import com.netcracker.library.entities.Entity;
import com.netcracker.library.entities.business.Rental;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.List;

/**
 * Created by raumo0 on 14.10.16.
 */
public class Book extends Entity {
    private static final long serialVersionUID = 1L;
    private BookPosition bookPosition;
    private BookEdition bookEdition;
    private BookState bookState;
    private List<Rental> rentals;

    public Book() {}

    public BookPosition getBookPosition() {
        return bookPosition;
    }

    public void setBookPosition(BookPosition bookPosition) {
        this.bookPosition = bookPosition;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(BookEdition bookEdition) {
        this.bookEdition = bookEdition;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (bookPosition != book.bookPosition) return false;
        if (bookEdition != null ? !bookEdition.equals(book.bookEdition) : book.bookEdition != null) return false;
        if (bookState != book.bookState) return false;
        return rentals != null ? rentals.equals(book.rentals) : book.rentals == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bookPosition != null ? bookPosition.hashCode() : 0);
        result = 31 * result + (bookEdition != null ? bookEdition.hashCode() : 0);
        result = 31 * result + (bookState != null ? bookState.hashCode() : 0);
        result = 31 * result + (rentals != null ? rentals.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookPosition=" + bookPosition +
                ", bookEdition=" + bookEdition +
                ", bookState=" + bookState +
                ", rentals=" + rentals +
                "} " + super.toString();
    }
}
