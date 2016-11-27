package com.netcracker.library.commands.user;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 27.11.16.
 */
public class UserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        int id;
        String page;
        User user = null;
        String value = req.getParameter(Parameters.ID);
        if (value == null) {
            return null;
        }
        try {
            id = Integer.parseInt(value);
            user = UserServiceImpl.getInstance().getUserById(id);
        } catch (ServiceException | NumberFormatException e) {
            e.printStackTrace();
        }
        if (user == null) {
            page = ConfigurationManager.getProperty(PageConstants.PAGE_NOT_FOUND);
            return page;
        }
        req.setAttribute(Parameters.NAVBAR_ACTIVE_PROFILE, Parameters.NAVBAR_ACTIVE);
        req.setAttribute(Parameters.PAGE_TITLE, user.getUsername());
        req.setAttribute(Parameters.USER_ID, user.getId());
        page = ConfigurationManager.getProperty(PageConstants.PROFILE);
        return page;
    }
}
