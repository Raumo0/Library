package com.netcracker.library.service.impl;

import com.netcracker.library.beans.Person;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.PersonDAO;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.PersonService;

import java.util.Collection;

/**
 * Created by raumo0 on 18.11.16.
 */
public class PersonServiceImpl implements PersonService {
    private DAOFactory factory;
    private PersonDAO personDAO;

    private PersonServiceImpl() {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        personDAO = factory.getPersonDAO();
    }

    public static PersonServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int insert(Person person) throws DAOException {
        return personDAO.insert(person);
    }

    @Override
    public Person getById(int id) throws DAOException {
//        return personDAO.getById(id);
        throw new DAOException();
    }

    @Override
    public boolean update(Person person) throws DAOException {
        throw new DAOException();
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        throw new DAOException();
    }

    @Override
    public Collection<Person> getAll() throws DAOException {
        throw new DAOException();
    }

    private static class SingletonHolder {
        private static final PersonServiceImpl INSTANCE = new PersonServiceImpl();
    }
}
