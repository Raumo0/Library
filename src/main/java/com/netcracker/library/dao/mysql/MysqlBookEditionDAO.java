package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Author;
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
    private static final String GET_BOOK_EDITION_BY_BOOK_ID = "SELECT be.id, be.title, be.page_count, be.release_year, " +
            "be.description, be.isbn, be.language_id, be.original_language_id, be.weight, be.bookbinding, be.image, " +
            "be.publisher_id, be.last_update FROM book_edition be INNER JOIN book b ON be.id = b.book_edition_id\n" +
            "WHERE b.id = ?";
    private static final String GET_BOOK_EDITIONS_BY_AUTHOR_ID = "SELECT be.id, be.title, be.page_count, " +
            "be.release_year, be.description, be.isbn, be.language_id, be.original_language_id, be.weight, " +
            "be.bookbinding, be.image, be.publisher_id, be.last_update FROM book_edition be " +
            "INNER JOIN author_has_book_edition a ON be.id = a.book_edition_id WHERE a.author_id = ?";
    private static final String INSERT_AUTHOR_BOOK_EDITION = "INSERT INTO author_has_book_edition " +
            "(author_id, book_edition_id) VALUES(?,?)";
    private static final String GET_BOOK_EDITIONS_BY_GAP = "SELECT * FROM book_edition LIMIT ?, ?";
    private static final String GET_NUMBER_OF_RECORDS = "SELECT count(*) FROM book_edition";

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

    public boolean createBookEditionWithAuthorRelation(BookEdition bookEdition) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        boolean created = false;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if (bookEdition.getAuthors() != null) {
                for (Author author: bookEdition.getAuthors()) {
                    statement = connection.prepareStatement(GET_BOOK_EDITIONS_BY_AUTHOR_ID);
                    statement.setString(1, String.valueOf(author.getId()));
                    result = statement.executeQuery();
                    if (!result.first()) {
                        statement = connection.prepareStatement(INSERT_AUTHOR_BOOK_EDITION);
                        statement.setInt(1, author.getId());
                        statement.setInt(2, bookEdition.getId());
                        statement.executeUpdate();
                    }
                    created = true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return created;
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
        boolean result = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, bookEdition.getTitle());
            statement.setInt(2, bookEdition.getPageCount());
            statement.setString(3, bookEdition.getDescription());
            statement.setInt(4, bookEdition.getWeight());
            statement.setString(5, bookEdition.getBookbinding().toString());
            statement.setInt(6, bookEdition.getId());
            if (statement.executeUpdate() != 0)
                result = true;
            if (bookEdition.getAuthors() != null) {
                for (Author author: bookEdition.getAuthors()) {
                    statement = connection.prepareStatement(INSERT_AUTHOR_BOOK_EDITION);
                    statement.setInt(1, author.getId());
                    statement.setInt(2, bookEdition.getId());
                    statement.executeUpdate();
                    result = true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
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
    public LinkedList<BookEdition> getBookEditionsByAuthorId(int authorId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        LinkedList<BookEdition> bookEditions = new LinkedList<>();
        BookEdition bookEdition;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BOOK_EDITIONS_BY_AUTHOR_ID);
            statement.setString(1, String.valueOf(authorId));
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
    public BookEdition getBookEditionByBookId(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        BookEdition bookEdition = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BOOK_EDITION_BY_BOOK_ID);
            statement.setString(1, String.valueOf(bookId));
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
    public Collection<BookEdition> getBookEditionsByGap(int offset, int quantity) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Collection<BookEdition> bookEditions = new LinkedList<>();
        BookEdition bookEdition;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BOOK_EDITIONS_BY_GAP);
            statement.setInt(1, offset);
            statement.setInt(2, quantity);
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
    public int getNumberOfRecords() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        int count = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_NUMBER_OF_RECORDS);
            result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count(*)");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return count;
    }
}
