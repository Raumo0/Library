package com.netcracker.library.commands.user;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.exceptions.ToolException;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;
import com.netcracker.library.tools.PasswordGenerator;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 26.11.16.
 */
public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String page;
        User user = new User();
        user.setFirstName(req.getParameter(Parameters.FIRST_NAME));
        user.setLastName(req.getParameter(Parameters.LAST_NAME));
        user.setUsername(req.getParameter(Parameters.USERNAME));
        user.setPassword(req.getParameter(Parameters.PASSWORD));
        user.setRole(UserRole.READER);
        if (!inspection(user)) {
            page = ConfigurationManager.getProperty(PageConstants.REGISTRATION);
            return page;
        }
        try {
            user.setSalt(PasswordGenerator.getInstance().generateSalt(user.getPassword()));
            user.setPassword(PasswordGenerator.getInstance().generatePassword(user.getPassword(), user.getSalt()));
            user.setId(UserServiceImpl.getInstance().addUser(user));

            req.getSession().setAttribute(Parameters.ROLE, user.getRole());
            req.getSession().setAttribute(Parameters.USERNAME, user.getUsername());
        } catch (ServiceException | ToolException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
            page = ConfigurationManager.getProperty(PageConstants.REGISTRATION);
            return page;
        }
        return null;
    }

    private boolean inspection(User user) {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getUsername().isEmpty()
                || user.getPassword().isEmpty())
            return false;
        if (user.getFirstName().length() > 45 || user.getLastName().length() > 45 || user.getUsername().length() > 45
                || user.getPassword().length() > 45)
            return false;
        return true;
    }
}
