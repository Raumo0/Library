package com.netcracker.library.service.impl;

import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.RentalDAO;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.BusinessService;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public class BusinessServiceImpl implements BusinessService {
    private DAOFactory factory;
    private RentalDAO rentalDAO;

    private BusinessServiceImpl() {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        rentalDAO = factory.getRentalDAO();
    }

    public static BusinessServiceImpl getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int addRental(Rental rental) throws DAOException {
        return rentalDAO.insert(rental);
    }

    @Override
    public Rental getRentalById(int id) throws DAOException {
        return rentalDAO.getById(id);
    }

    @Override
    public boolean updateRental(Rental rental) throws DAOException {
        return rentalDAO.update(rental);
    }

    @Override
    public boolean deleteRentalById(int id) throws DAOException {
        return rentalDAO.deleteById(id);
    }

    @Override
    public Collection<Rental> getRentalAll() throws DAOException {
        return rentalDAO.getAll();
    }

    @Override
    public boolean deleteRentalAll() throws DAOException {
        return rentalDAO.deleteAll();
    }

    @Override
    public Collection<Rental> getRentalsByUserId(int userId) throws DAOException {
        return rentalDAO.getRentalsByUserId(userId);
    }

    @Override
    public Collection<Rental> getRentalsByBookId(int bookId) throws DAOException {
        return rentalDAO.getRentalsByBookId(bookId);
    }

    private static class SingletonHolder{
        private static final BusinessServiceImpl INSTANCE = new BusinessServiceImpl();
    }
}
