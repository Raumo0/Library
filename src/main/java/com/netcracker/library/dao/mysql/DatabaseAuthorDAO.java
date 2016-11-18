package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.beans.books.Author;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.Collection;

/**
 * Created by raumo0 on 14.11.16.
 */
public class DatabaseAuthorDAO implements AuthorDAO {
    private static final String INSERT_NEW = "INSERT INTO reader VALUES(?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM reader";
    private static final String GET_BY_ID = "SELECT * FROM author WHERE id=?";
    private static final String INSERT = "INSERT INTO author (biography, person_id) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM reader WHERE id=?";
    private static final String UPDATE = "UPDATE reader set FIRST_NAME=?,LAST_NAME=?,EMAIL=?,LOGIN=?,PASSWORD=? WHERE id=?";

    private final static String TABLE_NAME = "author";

    protected DatabaseAuthorDAO() {}

    @Override
    public int insert(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getBiography());
            statement.setInt(2, author.getPersonId());
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
    public Author getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Author author = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                author = new Author();
                author.setId(result.getInt("id"));
                author.setBiography(result.getString("biography"));
                author.setPersonId(result.getInt("person_id"));
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
        throw new DAOException();
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        throw new DAOException();
    }

    @Override
    public Collection<Author> getAll() throws DAOException {
        throw new DAOException();
    }
}
