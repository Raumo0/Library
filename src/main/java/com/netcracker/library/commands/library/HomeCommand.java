package com.netcracker.library.commands.library;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.service.impl.BookServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by raumo0 on 27.11.16.
 */
public class HomeCommand implements Command{
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        int noOfPages;
        int pageNumber;
        int noOfRecords;
        int recordsPerPage;
        Collection<BookEdition> bookEditions = null;
        String value = req.getParameter(Parameters.PAGINATION_PARAMETER);
        try {
            pageNumber = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        recordsPerPage = Parameters.RECORDS_PER_PAGE;
        try {
            bookEditions = BookServiceImpl.getInstance().getBookEditionsByGap((pageNumber - 1) * recordsPerPage,
                    recordsPerPage);
            noOfRecords = BookServiceImpl.getInstance().numberOfRecords();
        } catch (ServiceException e) {
            e.printStackTrace();
            noOfRecords = bookEditions.size();
        }
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute(Parameters.BOOK_EDITIONS, bookEditions);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", pageNumber);
        page = ConfigurationManager.getProperty(PageConstants.INDEX);
        return page;
    }
}
