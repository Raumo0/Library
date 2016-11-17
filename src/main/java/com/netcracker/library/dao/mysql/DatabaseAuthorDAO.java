package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.beans.books.Author;

import java.sql.*;
import java.util.Collection;

/**
 * Created by raumo0 on 14.11.16.
 */
public class DatabaseAuthorDAO extends DatabaseAbstractDAO implements AuthorDAO {
    private static final String INSERT_NEW = "INSERT INTO reader VALUES(?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM reader";
    private static final String GET_BY_ID = "SELECT * FROM reader WHERE id=?";
    private static final String INSERT = "INSERT INTO author (biography, person_id) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM reader WHERE id=?";
    private static final String UPDATE = "UPDATE reader set FIRST_NAME=?,LAST_NAME=?,EMAIL=?,LOGIN=?,PASSWORD=? WHERE id=?";

    private final static String TABLE_NAME = "author";
    private Connection connection;

    protected DatabaseAuthorDAO(Connection connection) {
        super(connection, TABLE_NAME);
        this.connection = connection;
    }

    @Override
    public int insert(Author author) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author.getBiography());
            statement.setInt(2, author.getPersonId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Author getById(int id) {
        return null;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Collection<Author> getAll() {
        return null;
    }
}
