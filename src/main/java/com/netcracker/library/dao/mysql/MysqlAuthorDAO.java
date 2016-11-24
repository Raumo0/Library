package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.*;

/**
 * Created by raumo0 on 14.11.16.
 */
public class MysqlAuthorDAO extends MysqlPersonDAO implements AuthorDAO {
    private static final String GET_ALL = "SELECT * FROM author";
    private static final String GET_BY_ID = "SELECT * FROM author WHERE id=?";
    private static final String GET_PERSON_ID_BY_AUTHOR_ID = "SELECT person_id FROM author WHERE id=?";
    private static final String INSERT = "INSERT INTO author (biography, person_id) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM author WHERE id=?";
    private static final String UPDATE = "UPDATE author SET biography=? WHERE id=?";
    private static final String GET_AUTHOR_BY_PERSON_ID = "SELECT * FROM author WHERE person_id=?";
    private static final String GET_PERSONS_ID_BY_AUTHORS = "SELECT person_id FROM author";
    private static final String DELETE_ALL = "DELETE FROM author";
    private static final String INSERT_AUTHOR_BOOK_EDITION = "INSERT INTO author_has_book_edition " +
            "(author_id, book_edition_id) VALUES(?,?)";
    private static final String DELETE_AUTHOR_BOOK_EDITION = "DELETE FROM author_has_book_edition WHERE author_id=?";
    private static final String GET_AUTHORS_BY_BOOK_EDITION_ID = "SELECT a.id, a.biography, a.person_id, a.last_update " +
            "FROM author a INNER JOIN author_has_book_edition be ON be.author_id = a.id " +
            "WHERE be.book_edition_id = ?";

    public MysqlAuthorDAO() {}

    @Override
    public int insert(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        int authorId;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if (author.getPersonId() == 0)
                author.setPersonId(insertPerson(author, connection));
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getBiography());
            statement.setInt(2, author.getPersonId());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();
            result.first();
            authorId = result.getInt(1);
            if (author.getBookEditions() != null) {
                for (BookEdition bookEdition : author.getBookEditions()) {
                    statement = connection.prepareStatement(INSERT_AUTHOR_BOOK_EDITION);
                    statement.setInt(1, authorId);
                    statement.setInt(2, bookEdition.getId());
                    statement.executeUpdate();
                }
            }
            return authorId;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Author getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Author author = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                author = new Author();
                author.setId(result.getInt("id"));
                author.setPersonId(result.getInt("person_id"));
                author.setBiography(result.getString("biography"));
                getPersonById(author.getPersonId(), author, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return author;
    }

    @Override
    public boolean update(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, author.getBiography());
            statement.setInt(2, author.getId());
            statement.executeUpdate();

            statement = connection.prepareStatement(DELETE_AUTHOR_BOOK_EDITION);
            statement.setInt(1, author.getId());
            statement.executeUpdate();

            if (author.getBookEditions() != null) {
                for (BookEdition bookEdition : author.getBookEditions()) {
                    statement = connection.prepareStatement(INSERT_AUTHOR_BOOK_EDITION);
                    statement.setInt(1, author.getId());
                    statement.setInt(2, bookEdition.getId());
                    statement.executeUpdate();
                }
            }

            return updatePerson(author, connection);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        int personId;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_PERSON_ID_BY_AUTHOR_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                personId = result.getInt("person_id");
                statement = connection.prepareStatement(DELETE);
                statement.setInt(1, id);
                statement.executeUpdate();
                return deletePersonById(personId, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return false;
    }

    @Override
    public Author getAuthorByPersonId(int personId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        Author author = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_AUTHOR_BY_PERSON_ID);
            statement.setInt(1, personId);
            result = statement.executeQuery();
            while (result.next()) {
                author = new Author();
                author.setId(result.getInt("id"));
                author.setPersonId(result.getInt("person_id"));
                author.setBiography(result.getString("biography"));
                getPersonById(author.getPersonId(), author, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return author;
    }

    @Override
    public LinkedList<Author> getAuthorsByBookEditionId(int bookEditionId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        LinkedList<Author> authors = new LinkedList<>();
        Author author;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_AUTHORS_BY_BOOK_EDITION_ID);
            statement.setString(1, String.valueOf(bookEditionId));
            result = statement.executeQuery();
            while (result.next()) {
                author = new Author();
                author.setId(result.getInt("id"));
                author.setPersonId(result.getInt("person_id"));
                author.setBiography(result.getString("biography"));
                getPersonById(author.getPersonId(), author, connection);
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return authors;
    }

    @Override
    public Collection<Author> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        List<Author> authors = new ArrayList<>();
        ResultSet result;
        Author author;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                author = new Author();
                author.setId(result.getInt("id"));
                author.setPersonId(result.getInt("person_id"));
                author.setBiography(result.getString("biography"));
                getPersonById(author.getPersonId(), author, connection);
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return authors;
    }

    @Override
    public boolean deleteAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        int personId;
        boolean isPersonDelete = true;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_PERSONS_ID_BY_AUTHORS);
            ResultSet result = statement.executeQuery();
            statement = connection.prepareStatement(DELETE_ALL);
            statement.executeUpdate();
            while (result.next()) {
                personId = result.getInt("person_id");
                if (!deletePersonById(personId, connection))
                    isPersonDelete = false;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return isPersonDelete;
    }
}
