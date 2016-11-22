package com.netcracker.library.service.impl;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.BookService;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public class BookServiceImpl implements BookService {
    private DAOFactory factory;
    private BookDAO bookDAO;
    private BookEditionDAO bookEditionDAO;
    private AuthorDAO authorDAO;

    private BookServiceImpl(){
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        bookDAO = factory.getBookDAO();
        bookEditionDAO = factory.getBookEditionDAO();
        authorDAO = factory.getAuthorDAO();
    }

    public static BookServiceImpl getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int addBook(Book book) throws DAOException {
        return bookDAO.insert(book);
    }

    @Override
    public Book getBookById(int id) throws DAOException {
        return bookDAO.getById(id);
    }

    @Override
    public boolean updateBook(Book book) throws DAOException {
        return bookDAO.update(book);
    }

    @Override
    public boolean deleteBookById(int id) throws DAOException {
        return bookDAO.deleteById(id);
    }

    @Override
    public Collection<Book> getBookAll() throws DAOException {
        return bookDAO.getAll();
    }

    @Override
    public boolean deleteBookAll() throws DAOException {
        return bookDAO.deleteAll();
    }

    @Override
    public Book getBookByRentalId(int rentalId) throws DAOException {
        return bookDAO.getBookByRentalId(rentalId);
    }

    @Override
    public Collection<Book> getBooksByBookEditionId(int bookEditionId) throws DAOException {
        return bookDAO.getBooksByBookEditionId(bookEditionId);
    }

    @Override
    public int addBookEdition(BookEdition bookEdition) throws DAOException {
        return bookEditionDAO.insert(bookEdition);
    }

    @Override
    public BookEdition getBookEditionById(int id) throws DAOException {
        return bookEditionDAO.getById(id);
    }

    @Override
    public boolean updateBookEdition(BookEdition bookEdition) throws DAOException {
        return bookEditionDAO.update(bookEdition);
    }

    @Override
    public boolean deleteBookEditionById(int id) throws DAOException {
        return bookEditionDAO.deleteById(id);
    }

    @Override
    public Collection<BookEdition> getBookEditionAll() throws DAOException {
        return bookEditionDAO.getAll();
    }

    @Override
    public boolean deleteBookEditionAll() throws DAOException {
        return bookEditionDAO.deleteAll();
    }

    @Override
    public Collection<BookEdition> getBookEditionsByAuthorId(int authorId) throws DAOException {
        return bookEditionDAO.getBookEditionsByAuthorId(authorId);
    }

    @Override
    public BookEdition getBookEditionByBookId(int bookId) throws DAOException {
        return bookEditionDAO.getBookEditionByBookId(bookId);
    }

    @Override
    public int addAuthor(Author author) throws DAOException {
        return authorDAO.insert(author);
    }

    @Override
    public Author getAuthorById(int id) throws DAOException {
        return authorDAO.getById(id);
    }

    @Override
    public boolean updateAuthor(Author author) throws DAOException {
        return authorDAO.update(author);
    }

    @Override
    public boolean deleteAuthorById(int id) throws DAOException {
        return authorDAO.deleteById(id);
    }

    @Override
    public Collection<Author> getAuthorAll() throws DAOException {
        return authorDAO.getAll();
    }

    @Override
    public boolean deleteAuthorAll() throws DAOException {
        return authorDAO.deleteAll();
    }

    @Override
    public Collection<Author> getAuthorsByBookEditionId(int bookEditionId) throws DAOException {
        return authorDAO.getAuthorsByBookEditionId(bookEditionId);
    }

    private static class SingletonHolder{
        private static final BookServiceImpl INSTANCE = new BookServiceImpl();
    }
}
