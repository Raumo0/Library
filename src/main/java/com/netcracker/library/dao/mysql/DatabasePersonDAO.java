package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.Person;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.Collection;

/**
 * Created by raumo0 on 14.11.16.
 */
public abstract class DatabasePersonDAO {
    private static final String GET_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String INSERT = "INSERT INTO person (first_name,last_name) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM person WHERE id=?";
    private static final String UPDATE = "UPDATE person SET first_name=?,last_name=? WHERE id=?";

    protected DatabasePersonDAO() { }

    protected int insertPerson(Person person, Connection connection) throws DAOException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected Person getPersonById(int id, Person person, Connection connection) throws DAOException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                person.setPersonId(result.getInt("id"));
                person.setFirstName(result.getString("first_name"));
                person.setLastName(result.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return person;
    }

    protected boolean updatePerson(Person person, Connection connection) throws DAOException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getPersonId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return true;
    }

    protected boolean deletePersonById(int id, Connection connection) throws DAOException {
        PreparedStatement statement;
        DAOManager daoManager = DAOManager.getInstance();
        try {
            if (daoManager.getUserDAO().getUserByPersonId(id) != null ||
                    daoManager.getAuthorDAO().getAuthorByPersonId(id) != null)
                throw new DAOException();

            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected Collection<Person> getPersonAll() throws DAOException {
        throw new DAOException();
    }
}
