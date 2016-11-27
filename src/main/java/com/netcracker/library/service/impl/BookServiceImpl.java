package com.netcracker.library.service.impl;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.exceptions.ServiceException;
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
    public int addBook(Book book) throws ServiceException {
        try {
            return bookDAO.insert(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book getBookById(int id) throws ServiceException {
        try {
            return bookDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBook(Book book) throws ServiceException {
        try {
            return bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookById(int id) throws ServiceException {
        try {
            return bookDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Book> getBookAll() throws ServiceException {
        try {
            return bookDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookAll() throws ServiceException {
        try {
            return bookDAO.deleteAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book getBookByRentalId(int rentalId) throws ServiceException {
        try {
            return bookDAO.getBookByRentalId(rentalId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Book> getBooksByBookEditionId(int bookEditionId) throws ServiceException {
        try {
            return bookDAO.getBooksByBookEditionId(bookEditionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addBookEdition(BookEdition bookEdition) throws ServiceException {
        try {
            return bookEditionDAO.insert(bookEdition);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BookEdition getBookEditionById(int id) throws ServiceException {
        try {
            return bookEditionDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBookEdition(BookEdition bookEdition) throws ServiceException {
        try {
            return bookEditionDAO.update(bookEdition);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookEditionById(int id) throws ServiceException {
        try {
            return bookEditionDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<BookEdition> getBookEditionAll() throws ServiceException {
        try {
            return bookEditionDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookEditionAll() throws ServiceException {
        try {
            return bookEditionDAO.deleteAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<BookEdition> getBookEditionsByAuthorId(int authorId) throws ServiceException {
        try {
            return bookEditionDAO.getBookEditionsByAuthorId(authorId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BookEdition getBookEditionByBookId(int bookId) throws ServiceException {
        try {
            return bookEditionDAO.getBookEditionByBookId(bookId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<BookEdition> getBookEditionsByGap(int offset, int quantity) throws ServiceException {
        try {
            return bookEditionDAO.getBookEditionsByGap(offset, quantity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int numberOfRecords() throws ServiceException {
        try {
            return bookEditionDAO.getNumberOfRecords();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addAuthor(Author author) throws ServiceException {
        try {
            return authorDAO.insert(author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Author getAuthorById(int id) throws ServiceException {
        try {
            return authorDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateAuthor(Author author) throws ServiceException {
        try {
            return authorDAO.update(author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAuthorById(int id) throws ServiceException {
        try {
            return authorDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Author> getAuthorAll() throws ServiceException {
        try {
            return authorDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAuthorAll() throws ServiceException {
        try {
            return authorDAO.deleteAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Author getAuthorByPersonId(int personId) throws ServiceException {
        try {
            return authorDAO.getAuthorByPersonId(personId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Author> getAuthorsByBookEditionId(int bookEditionId) throws ServiceException {
        try {
            return authorDAO.getAuthorsByBookEditionId(bookEditionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static class SingletonHolder{
        private static final BookServiceImpl INSTANCE = new BookServiceImpl();
    }
}
