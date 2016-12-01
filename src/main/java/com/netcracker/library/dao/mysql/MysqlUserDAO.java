package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.UserDAO;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 19.11.16.
 */
public class MysqlUserDAO extends MysqlPersonDAO implements UserDAO {
    private static final String INSERT = "INSERT INTO user (person_id, username, password, salt, role) VALUES(?,?,?,?,?)";
    private static final String GET_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String UPDATE = "UPDATE user SET username=?,password=?,salt=?,role=? WHERE id=?";
    private static final String DELETE = "DELETE FROM user WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM user";
    private static final String DELETE_ALL = "DELETE FROM user";
    private static final String GET_PERSON_ID_BY_USER_ID = "SELECT person_id FROM user WHERE id=?";
    private static final String GET_PERSONS_ID_BY_USERS = "SELECT person_id FROM user";
    private static final String GET_USER_BY_PERSON_ID = "SELECT * FROM user WHERE person_id=?";
    private static final String GET_USER_BY_RENTAL_ID = "SELECT u.id, u.person_id, u.username, u.password, " +
            "u.salt, u.address_id, u.mobile_phone, u.email, u.role, u.last_update FROM user u " +
            "INNER JOIN rental r ON u.id = r.user_id WHERE r.id=?";
    private static final String GET_STAFF_USER_BY_RENTAL_ID = "SELECT u.id, u.person_id, u.username, " +
            "u.password, u.salt, u.address_id, u.mobile_phone, u.email, u.role, u.last_update " +
            "FROM user u INNER JOIN rental r ON u.id = r.staff_user_id WHERE r.id=?";
    private static final String GET_USERS_BY_ROLE_ID = "SELECT * FROM user WHERE role=?";
    public static final String CHECK_AUTHORIZATION = "SELECT * FROM user WHERE username=? AND password=? AND salt=?";

    public MysqlUserDAO() {
    }

    @Override
    public int insert(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if (user.getPersonId() == 0)
                user.setPersonId(insertPerson(user, connection));
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getPersonId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSalt());
            statement.setString(5, user.getRole().toString());
            statement.executeUpdate();
            result = statement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public User getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public boolean update(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.setString(4, user.getRole().toString());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            return updatePerson(user, connection);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int personId;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_PERSON_ID_BY_USER_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
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
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    public User getUserByPersonId(int personId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_PERSON_ID);
            statement.setInt(1, personId);
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public User getUserByRentalId(int rentalId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_RENTAL_ID);
            statement.setString(1, String.valueOf(rentalId));
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public User getStaffUserByRentalId(int rentalId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_STAFF_USER_BY_RENTAL_ID);
            statement.setString(1, String.valueOf(rentalId));
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public LinkedList<User> getUsersByRole(UserRole role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user;
        LinkedList<User> users = new LinkedList<>();
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USERS_BY_ROLE_ID);
            statement.setString(1, role.toString());
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return users;
    }

    @Override
    public User isAuthorized(String username, String password, String salt) throws DAOException {
        User user = null;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CHECK_AUTHORIZATION);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, salt);
            result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }

    @Override
    public LinkedList<User> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        LinkedList<User> users = new LinkedList<>();
        ResultSet result = null;
        User user;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setPersonId(result.getInt("person_id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setSalt(result.getString("salt"));
                user.setRole(UserRole.valueOf(result.getString("role").toUpperCase()));
                getPersonById(user.getPersonId(), user, connection);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return users;
    }

    @Override
    public boolean deleteAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        int personId;
        boolean isPersonDelete = true;
        ResultSet result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_PERSONS_ID_BY_USERS);
            result = statement.executeQuery();
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
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return isPersonDelete;
    }
}
