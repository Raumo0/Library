package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 21.11.16.
 */
public class MysqlBookDAO implements BookDAO {
    private static final String GET_ALL = "SELECT * FROM book";
    private static final String GET_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String INSERT = "INSERT INTO book (book_edition_id, position, state) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM book WHERE id=?";
    private static final String UPDATE = "UPDATE book SET position=?,state=? WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM book";
    private static final String GET_BOOKS_BY_BOOK_EDITION_ID = "SELECT * FROM book WHERE book_edition_id=?";
    private static final String GET_BOOK_BY_RENTAL_ID = "SELECT b.id, b.book_edition_id, b.position, " +
            "b.state, b.last_update FROM book b INNER JOIN rental r ON r.book_id = b.id WHERE r.id = ?";

    public MysqlBookDAO() {}

    @Override
    public int insert(Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, book.getBookEdition().getId());
            statement.setString(2, book.getBookPosition().toString());
            statement.setString(3, book.getBookState().toString());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Book getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Book book = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setBookState(BookState.valueOf(result.getString("state").toUpperCase()));
                book.setBookPosition(BookPosition.valueOf(result.getString("position").toUpperCase()));
                book.setBookEdition(
                        DAOManager.getInstance().getBookEditionDAO().getById(result.getInt(
                                "book_edition_id")));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return book;
    }

    @Override
    public boolean update(Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, book.getBookPosition().toString());
            statement.setString(2, book.getBookState().toString());
            statement.setInt(3, book.getId());
            if (statement.executeUpdate() == 0)
                return false;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Collection<Book> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        Book book;
        List<Book> books = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setBookState(BookState.valueOf(result.getString("state").toUpperCase()));
                book.setBookPosition(BookPosition.valueOf(result.getString("position").toUpperCase()));
                book.setBookEdition(
                        DAOManager.getInstance().getBookEditionDAO().getById(result.getInt(
                                "book_edition_id")));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return books;
    }

    @Override
    public boolean deleteAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_ALL);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Book getBookByRentalId(int rentalId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Book book = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BOOK_BY_RENTAL_ID);
            statement.setString(1, String.valueOf(rentalId));
            result = statement.executeQuery();
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setBookState(BookState.valueOf(result.getString("state").toUpperCase()));
                book.setBookPosition(BookPosition.valueOf(result.getString("position").toUpperCase()));
                book.setBookEdition(
                        DAOManager.getInstance().getBookEditionDAO().getById(result.getInt(
                                "book_edition_id")));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return book;
    }

    @Override
    public LinkedList<Book> getBooksByBookEditionId(int bookEditionId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Book book;
        LinkedList<Book> books = new LinkedList<>();
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BOOKS_BY_BOOK_EDITION_ID);
            statement.setString(1, String.valueOf(bookEditionId));
            result = statement.executeQuery();
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setBookState(BookState.valueOf(result.getString("state").toUpperCase()));
                book.setBookPosition(BookPosition.valueOf(result.getString("position").toUpperCase()));
                book.setBookEdition(
                        DAOManager.getInstance().getBookEditionDAO().getById(result.getInt(
                                "book_edition_id")));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return books;
    }
}
