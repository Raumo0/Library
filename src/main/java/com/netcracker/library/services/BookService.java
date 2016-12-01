package com.netcracker.library.services;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.exceptions.ServiceException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface BookService {
    int addBook(Book book) throws ServiceException;

    Book getBookById(int id) throws ServiceException;

    boolean updateBook(Book book) throws ServiceException;

    boolean deleteBookById(int id) throws ServiceException;

    Collection<Book> getBookAll() throws ServiceException;

    boolean deleteBookAll() throws ServiceException;

    Book getBookByRentalId(int rentalId) throws ServiceException;

    Collection<Book> getBooksByBookEditionId(int bookEditionId) throws ServiceException;

    int addBookEdition(BookEdition bookEdition) throws ServiceException;

    BookEdition getBookEditionById(int id) throws ServiceException;

    boolean updateBookEdition(BookEdition bookEdition) throws ServiceException;

    boolean deleteBookEditionById(int id) throws ServiceException;

    Collection<BookEdition> getBookEditionAll() throws ServiceException;

    boolean deleteBookEditionAll() throws ServiceException;

    Collection<BookEdition> getBookEditionsByAuthorId(int authorId) throws ServiceException;

    BookEdition getBookEditionByBookId(int bookId) throws ServiceException;

    Collection<BookEdition> getBookEditionsByGap(int offset, int quantity) throws ServiceException;

    int numberOfBookEditionRecords() throws ServiceException;

    int addAuthor(Author author) throws ServiceException;

    Author getAuthorById(int id) throws ServiceException;

    boolean updateAuthor(Author author) throws ServiceException;

    boolean deleteAuthorById(int id) throws ServiceException;

    Collection<Author> getAuthorAll() throws ServiceException;

    boolean deleteAuthorAll() throws ServiceException;

    Author getAuthorByPersonId(int personId) throws ServiceException;

    Collection<Author> getAuthorsByBookEditionId(int bookEditionId) throws ServiceException;
}
