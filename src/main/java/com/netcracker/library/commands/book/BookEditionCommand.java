package com.netcracker.library.commands.book;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.services.impl.BookServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 27.11.16.
 */
public class BookEditionCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int id;
        String page;
        BookEdition bookEdition = null;
        String value = req.getParameter(Parameters.ID);
        if (value == null) {
            return null;
        }
        try {
            id = Integer.parseInt(value);
            bookEdition = BookServiceImpl.getInstance().getBookEditionById(id);
        } catch (ServiceException | NumberFormatException e) {
            e.printStackTrace();
        }
        if (bookEdition == null) {
            page = ConfigurationManager.getProperty(PageConstants.PAGE_NOT_FOUND);
            return page;
        }
        req.setAttribute(Parameters.PAGE_TITLE, bookEdition.getTitle());
        req.setAttribute(Parameters.BOOK_EDITION_ID, bookEdition.getId());
        req.setAttribute(Parameters.BOOK_EDITION_TITLE, bookEdition.getTitle());
        req.setAttribute(Parameters.BOOK_EDITION_PAGE_COUNT, bookEdition.getPageCount());
        req.setAttribute(Parameters.BOOK_EDITION_DESCRIPTION, bookEdition.getDescription());
        req.setAttribute(Parameters.BOOK_EDITION_ISBN, bookEdition.getIsbn());
        req.setAttribute(Parameters.BOOK_EDITION_BOOKBINDING, bookEdition.getBookbinding());
        page = ConfigurationManager.getProperty(PageConstants.BOOK_EDITION);
        return page;
    }
}
