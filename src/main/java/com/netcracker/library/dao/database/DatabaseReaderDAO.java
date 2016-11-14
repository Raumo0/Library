package com.netcracker.library.dao.database;

import com.netcracker.library.dao.ReaderDAO;
import com.netcracker.library.entities.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raumo0 on 04.11.16.
 */
public class DatabaseReaderDAO extends DatabaseAbstractDAO implements ReaderDAO {
    private static final String INSERT_NEW = "INSERT INTO reader VALUES(?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM reader";
    private static final String GET_BY_ID = "SELECT * FROM reader WHERE id=?";
    private static final String INSERT = "INSERT INTO reader (FIRST_NAME,LAST_NAME,EMAIL,LOGIN,PASSWORD) VALUES(?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM reader WHERE id=?";
    private static final String UPDATE = "UPDATE reader set FIRST_NAME=?,LAST_NAME=?,EMAIL=?,LOGIN=?,PASSWORD=? WHERE id=?";

    private final static String TABLE_NAME = "reader";
    private Connection connection;

    public DatabaseReaderDAO(Connection connection){
        super(connection, TABLE_NAME);
        this.connection = connection;
    }

    @Override
    public int insert(Reader reader) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reader.getFirstName());
            preparedStatement.setString(2, reader.getLastName());
            preparedStatement.setString(3, reader.getEmail());
            preparedStatement.setString(4, reader.getLogin());
            preparedStatement.setString(5, reader.getPassword());
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            result.first();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Reader read(int id) {
        Reader reader = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            reader = new Reader(result.getString("FIRST_NAME"),
                    result.getString("LAST_NAME"),
                    result.getString("EMAIL"),
                    result.getInt("ID"),
                    result.getString("LOGIN"),
                    result.getString("PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Override
    public boolean update(Reader reader) {
        PreparedStatement preparedStatement;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, reader.getFirstName());
            preparedStatement.setString(2, reader.getLastName());
            preparedStatement.setString(3, reader.getEmail());
            preparedStatement.setString(4, reader.getLogin());
            preparedStatement.setString(5, reader.getPassword());
            preparedStatement.setInt(6, reader.getId());
            preparedStatement.executeUpdate();
            preparedStatement.getGeneratedKeys();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveAll(Collection<Reader> readers) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            for (Reader reader : readers) {
                preparedStatement.setInt(1, reader.getId());
                preparedStatement.setString(2, reader.getFirstName());
                preparedStatement.setString(3, reader.getLastName());
                preparedStatement.setString(4, reader.getEmail());
                preparedStatement.setString(5, reader.getLogin());
                preparedStatement.setString(6, reader.getPassword());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Reader> readAll() {
        Collection<Reader> readers = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Reader reader = new Reader(result.getString("FIRST_NAME"),
                        result.getString("LAST_NAME"),
                        result.getString("EMAIL"),
                        result.getInt("ID"),
                        result.getString("LOGIN"),
                        result.getString("PASSWORD"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }
}
