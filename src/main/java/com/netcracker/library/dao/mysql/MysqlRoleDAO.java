package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.users.Role;
import com.netcracker.library.dao.RoleDAO;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by raumo0 on 21.11.16.
 */
public class MysqlRoleDAO implements RoleDAO {
    private static final String GET_ALL = "SELECT * FROM role";
    private static final String GET_BY_ID = "SELECT * FROM role WHERE id=?";
    private static final String INSERT = "INSERT INTO role (name, description) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM role WHERE id=?";
    private static final String UPDATE = "UPDATE role SET description=? WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM role";
    private static final String GET_ROLE_BY_USER_ID = "SELECT r.id, r.name, r.description, r.last_update  FROM role r " +
            "INNER JOIN user u ON u.role_id = r.id WHERE u.id=?";

    public MysqlRoleDAO() {}

    @Override
    public int insert(Role role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, role.getName());
            statement.setString(2, role.getDescription());
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
    public Role getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Role role = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
                role.setDescription(result.getString("description"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public boolean update(Role role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, role.getDescription());
            statement.setInt(2, role.getId());
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
    public Collection<Role> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        Role role;
        List<Role> roles = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
                role.setDescription(result.getString("description"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return roles;
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
    public Role getRoleByUserId(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Role role = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ROLE_BY_USER_ID);
            statement.setString(1, String.valueOf(userId));
            result = statement.executeQuery();
            while (result.next()) {
                role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
                role.setDescription(result.getString("description"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return role;
    }
}
