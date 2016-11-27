package com.netcracker.library.commands.library;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.service.impl.BookServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by raumo0 on 27.11.16.
 */
public class HomeCommand implements Command{
    @Override
    public String execute(HttpServletRequest req) {
        //todo fix this
//        int id;
        String page;
        BookEdition bookEdition = null;
        Collection<BookEdition> bookEditions = null;
//        String value = req.getParameter(Parameters.ID);
//        if (value == null) {
//            return null;
//        }
        try {
//            id = Integer.parseInt(value);
            bookEditions = BookServiceImpl.getInstance().getBookEditionAll();
        } catch (ServiceException | NumberFormatException e) {
            e.printStackTrace();
        }
        if (bookEditions != null && bookEditions.size() != 0) {
            req.setAttribute(Parameters.BOOK_EDITIONS, bookEditions);


            int pageNumber = 1;
            int recordsPerPage = 5;
            if(req.getParameter("page") != null)
                pageNumber = Integer.parseInt(req.getParameter("page"));


//            int noOfRecords = dao.getNoOfRecords();
//            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//            req.setAttribute("noOfPages", noOfPages);
//            req.setAttribute("currentPage", pageNumber);
            req.setAttribute("noOfPages", 3);
            req.setAttribute("currentPage", 2);
        }
        page = ConfigurationManager.getProperty(PageConstants.INDEX);
        return page;
    }
}
