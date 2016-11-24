package com.netcracker.library.service;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface BookService {
    int addBook(Book book) throws DAOException;

    Book getBookById(int id) throws DAOException;

    boolean updateBook(Book book) throws DAOException;

    boolean deleteBookById(int id) throws DAOException;

    Collection<Book> getBookAll() throws DAOException;

    boolean deleteBookAll() throws DAOException;

    Book getBookByRentalId(int rentalId) throws DAOException;

    Collection<Book> getBooksByBookEditionId(int bookEditionId) throws DAOException;

    int addBookEdition(BookEdition bookEdition) throws DAOException;

    BookEdition getBookEditionById(int id) throws DAOException;

    boolean updateBookEdition(BookEdition bookEdition) throws DAOException;

    boolean deleteBookEditionById(int id) throws DAOException;

    Collection<BookEdition> getBookEditionAll() throws DAOException;

    boolean deleteBookEditionAll() throws DAOException;

    Collection<BookEdition> getBookEditionsByAuthorId(int authorId) throws DAOException;

    BookEdition getBookEditionByBookId(int bookId) throws DAOException;

    int addAuthor(Author author) throws DAOException;

    Author getAuthorById(int id) throws DAOException;

    boolean updateAuthor(Author author) throws DAOException;

    boolean deleteAuthorById(int id) throws DAOException;

    Collection<Author> getAuthorAll() throws DAOException;

    boolean deleteAuthorAll() throws DAOException;

    Author getAuthorByPersonId(int personId) throws DAOException;

    Collection<Author> getAuthorsByBookEditionId(int bookEditionId) throws DAOException;
}
