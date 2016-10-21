package com.netcracker.library.dao.text;

import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.text.TextBookDAO;

/**
 * Created by raumo0 on 20.10.16.
 */
public class TextDAOFactory extends DAOFactory {
    @Override
    public BookDAO getBookDAO() {
        return new TextBookDAO();
    }
}
