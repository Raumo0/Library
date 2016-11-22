package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.enums.Bookbinding;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.*;

/**
 * Created by raumo0 on 20.11.16.
 */
public class MysqlBookEditionDAO implements BookEditionDAO {
    private static final String GET_ALL = "SELECT * FROM book_edition";
    private static final String GET_BY_ID = "SELECT * FROM book_edition WHERE id=?";
    private static final String INSERT = "INSERT INTO book_edition (title, page_count, description, " +
            "isbn, weight, bookbinding) VALUES(?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM book_edition WHERE id=?";
    private static final String UPDATE = "UPDATE book_edition SET title=?, page_count=?, description=?," +
            "weight=?,bookbinding=? WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM book_edition";

    public MysqlBookEditionDAO() {}

    @Override
    public int insert(BookEdition bookEdition) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bookEdition.getTitle());
            statement.setInt(2, bookEdition.getPageCount());
//            statement.setInt(3, bookEdition.getReleaseYear().get(Calendar.YEAR));
            statement.setString(3, bookEdition.getDescription());
            statement.setInt(4, bookEdition.getIsbn());
            statement.setInt(5, bookEdition.getWeight());
            statement.setString(6, bookEdition.getBookbinding().toString());
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
    public BookEdition getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        BookEdition bookEdition = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                bookEdition = new BookEdition();
                bookEdition.setId(result.getInt("id"));
                bookEdition.setTitle(result.getString("title"));
                bookEdition.setPageCount(result.getInt("page_count"));
                bookEdition.setDescription(result.getString("description"));
                bookEdition.setIsbn(result.getInt("isbn"));
                bookEdition.setWeight(result.getInt("weight"));
                bookEdition.setBookbinding(Bookbinding.valueOf(result.getString("bookbinding").toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return bookEdition;
    }

    @Override
    public boolean update(BookEdition bookEdition) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, bookEdition.getTitle());
            statement.setInt(2, bookEdition.getPageCount());
            statement.setString(3, bookEdition.getDescription());
            statement.setInt(4, bookEdition.getWeight());
            statement.setString(5, bookEdition.getBookbinding().toString());
            statement.setInt(6, bookEdition.getId());
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
    public Collection<BookEdition> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        BookEdition bookEdition;
        List<BookEdition> bookEditions = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                bookEdition = new BookEdition();
                bookEdition.setId(result.getInt("id"));
                bookEdition.setTitle(result.getString("title"));
                bookEdition.setPageCount(result.getInt("page_count"));
                bookEdition.setDescription(result.getString("description"));
                bookEdition.setIsbn(result.getInt("isbn"));
                bookEdition.setWeight(result.getInt("weight"));
                bookEdition.setBookbinding(Bookbinding.valueOf(result.getString("bookbinding").toUpperCase()));
                bookEditions.add(bookEdition);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return bookEditions;
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
    public Collection<BookEdition> getBookEditionsByAuthorId(int authorId) throws DAOException {
        //TODO
        throw new DAOException();
    }

    @Override
    public BookEdition getBookEditionByBookId(int bookId) throws DAOException {
        //TODO
        throw new DAOException();
    }
}
