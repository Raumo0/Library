package com.netcracker.library.dao;

import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.exceptions.DAOException;

import java.util.LinkedList;

/**
 * Created by raumo0 on 20.11.16.
 */
public interface RentalDAO extends AbstractDAO<Rental> {
    LinkedList<Rental> getRentalsByUserId(int userId) throws DAOException;

    LinkedList<Rental> getRentalsByBookId(int bookId) throws DAOException;
}
