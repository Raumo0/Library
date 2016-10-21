package com.netcracker.library.dao;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.BookEdition;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.Collection;

/**
 * Created by raumo0 on 20.10.16.
 */
public class ExampleRunDAO {
    public void runDao() {

// create the required DAO Factory
        DAOFactory textFactory = DAOFactory.getDAOFactory(DAOFactory.BINARY);

// Create a DAO
        BookDAO bookDAO = textFactory.getBookDAO();


        BookEdition newBookEdition = new BookEdition(11, "The best book!");
        Book newBook = new Book(54, newBookEdition, BookState.GOOD, BookPosition.IN_STORE);

// create a new book
        long newBookID = bookDAO.insertBook(newBook);
//        newBook.setBookState(BookState.BAD);
//        newBookID = bookDAO.insertBook(newBook);

// Find a book object. Get the Transfer Object.
        Book book = bookDAO.findBook(54);

// modify the values in the Transfer Object.
        book.setBookState(BookState.BAD);
// update the book object using the DAO
        bookDAO.updateBook(book);

// delete a book object
        bookDAO.deleteBook(54);
// select all books in the same city
        Book criteria = new Book(98, newBookEdition, BookState.EXCELLENT, BookPosition.IN_STORE);
        criteria.setBookPosition(BookPosition.IN_READING_ROOM);
        Collection booksList = bookDAO.selectBooksByState(BookState.EXCELLENT);
        Collection booksList2 = bookDAO.selectBooksByPosition(BookPosition.IN_READING_ROOM);
// returns booksList - collection of Book
// Transfer Objects. iterate through this collection to
// get values.
        System.out.println(newBookEdition.equals(book.getBookEdition()));
    }
}
