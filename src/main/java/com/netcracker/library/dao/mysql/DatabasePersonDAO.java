package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.PersonDAO;
import com.netcracker.library.beans.Person;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.Collection;

/**
 * Created by raumo0 on 14.11.16.
 */
public class DatabasePersonDAO implements PersonDAO {
    private static final String INSERT_NEW = "INSERT INTO reader VALUES(?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM reader";
    private static final String GET_BY_ID = "SELECT * FROM reader WHERE id=?";
    private static final String INSERT = "INSERT INTO person (first_name,last_name) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM reader WHERE id=?";
    private static final String UPDATE = "UPDATE reader set FIRST_NAME=?,LAST_NAME=?,EMAIL=?,LOGIN=?,PASSWORD=? WHERE id=?";

    private final static String TABLE_NAME = "person";

    protected DatabasePersonDAO() { }

    @Override
    public int insert(Person person) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Collection<Person> getAll() {
        return null;
    }
}
