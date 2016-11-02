package com.netcracker.library.dao.text;

import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.ReaderDAO;

/**
 * Created by raumo0 on 20.10.16.
 */
public class TextDAOFactory extends DAOFactory {
    @Override
    public ReaderDAO getReaderDAO() {
        return new TextReaderDAO();
    }
}
