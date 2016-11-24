package com.netcracker.library.dao.mysql;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.RentalDAO;
import com.netcracker.library.enums.BookIssue;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raumo0 on 21.11.16.
 */
public class MysqlRentalDAO implements RentalDAO {
    private static final String GET_ALL = "SELECT * FROM rental";
    private static final String GET_BY_ID = "SELECT * FROM rental WHERE id=?";
    private static final String INSERT = "INSERT INTO rental (comment, user_id, book_id, book_state_before," +
            "issue, staff_user_id) VALUES(?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM rental WHERE id=?";
    private static final String UPDATE = "UPDATE rental SET book_state_after=?,staff_user_id=? WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM rental";
    private static final String GET_RENTAL_BY_USER_ID = "SELECT * FROM rental WHERE user_id=?";
    private static final String GET_RENTAL_BY_BOOK_ID = "SELECT * FROM rental WHERE book_id=?";

    public MysqlRentalDAO() {}

    @Override
    public int insert(Rental rental) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, rental.getComment());
            statement.setInt(2, rental.getUser().getId());
            statement.setInt(3, rental.getBook().getId());
            statement.setString(4, rental.getStateBefore().toString());
            statement.setString(5, rental.getBookIssue().toString());
            if (rental.getStaff_user() != null)
                statement.setInt(6, rental.getStaff_user().getId());
            else
                statement.setNull(6, Types.INTEGER);
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
    public Rental getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Rental rental = null;
        ResultSet result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setString(1, String.valueOf(id));
            result = statement.executeQuery();
            while (result.next()) {
                rental = new Rental();
                rental.setId(result.getInt("id"));
                rental.setComment(result.getString("comment"));
                User user = DAOManager.getInstance().getUserDAO().getById(result.getInt("user_id"));
                rental.setUser(user);
                Book book = DAOManager.getInstance().getBookDAO().getById(result.getInt("book_id"));
                rental.setBook(book);
                rental.setStateBefore(BookState.valueOf(result.getString("book_state_before").toUpperCase()));
                rental.setBookIssue(BookIssue.valueOf(result.getString("issue").toUpperCase()));
                String stateAfter = result.getString("book_state_after");
                if (stateAfter != null)
                    rental.setStateAfter(BookState.valueOf(stateAfter.toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return rental;
    }

    @Override
    public boolean update(Rental rental) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, rental.getStateAfter().toString());
            if (rental.getStaff_user() != null)
                statement.setInt(2, rental.getStaff_user().getId());
            else
                statement.setNull(2, Types.INTEGER);
            statement.setInt(3, rental.getId());
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
    public LinkedList<Rental> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet result;
        Rental rental;
        LinkedList<Rental> rentals = new LinkedList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL);
            result = statement.executeQuery();
            while (result.next()) {
                rental = new Rental();
                rental.setId(result.getInt("id"));
                rental.setComment(result.getString("comment"));
                User user = DAOManager.getInstance().getUserDAO().getById(result.getInt("user_id"));
                rental.setUser(user);
                Book book = DAOManager.getInstance().getBookDAO().getById(result.getInt("book_id"));
                rental.setBook(book);
                rental.setStateBefore(BookState.valueOf(result.getString("book_state_before").toUpperCase()));
                rental.setBookIssue(BookIssue.valueOf(result.getString("issue").toUpperCase()));
                String stateAfter = result.getString("book_state_after");
                if (stateAfter != null)
                    rental.setStateAfter(BookState.valueOf(stateAfter.toUpperCase()));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return rentals;
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
    public LinkedList<Rental> getRentalsByUserId(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Rental rental;
        ResultSet result;
        LinkedList<Rental> rentals = new LinkedList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_RENTAL_BY_USER_ID);
            statement.setString(1, String.valueOf(userId));
            result = statement.executeQuery();
            while (result.next()) {
                rental = new Rental();
                rental.setId(result.getInt("id"));
                rental.setComment(result.getString("comment"));
                User user = DAOManager.getInstance().getUserDAO().getById(result.getInt("user_id"));
                rental.setUser(user);
                Book book = DAOManager.getInstance().getBookDAO().getById(result.getInt("book_id"));
                rental.setBook(book);
                rental.setStateBefore(BookState.valueOf(result.getString("book_state_before").toUpperCase()));
                rental.setBookIssue(BookIssue.valueOf(result.getString("issue").toUpperCase()));
                String stateAfter = result.getString("book_state_after");
                if (stateAfter != null)
                    rental.setStateAfter(BookState.valueOf(stateAfter.toUpperCase()));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return rentals;
    }

    @Override
    public LinkedList<Rental> getRentalsByBookId(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement;
        Rental rental;
        ResultSet result;
        LinkedList<Rental> rentals = new LinkedList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(GET_RENTAL_BY_BOOK_ID);
            statement.setString(1, String.valueOf(bookId));
            result = statement.executeQuery();
            while (result.next()) {
                rental = new Rental();
                rental.setId(result.getInt("id"));
                rental.setComment(result.getString("comment"));
                User user = DAOManager.getInstance().getUserDAO().getById(result.getInt("user_id"));
                rental.setUser(user);
                Book book = DAOManager.getInstance().getBookDAO().getById(result.getInt("book_id"));
                rental.setBook(book);
                rental.setStateBefore(BookState.valueOf(result.getString("book_state_before").toUpperCase()));
                rental.setBookIssue(BookIssue.valueOf(result.getString("issue").toUpperCase()));
                String stateAfter = result.getString("book_state_after");
                if (stateAfter != null)
                    rental.setStateAfter(BookState.valueOf(stateAfter.toUpperCase()));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return rentals;
    }
}
