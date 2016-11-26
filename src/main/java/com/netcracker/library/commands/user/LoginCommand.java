package com.netcracker.library.commands.user;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.MessageConstants;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.exceptions.ToolException;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.ConfigurationManager;
import com.netcracker.library.tools.MessageManager;
import com.netcracker.library.tools.PasswordGenerator;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 27.11.16.
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(Parameters.USERNAME);
        String password = req.getParameter(Parameters.PASSWORD);
        String salt;
        User user;
        String page;
        if (!(username.isEmpty() || password.isEmpty() ||
                username.length() > 45 || password.length() > 45)) {
            try {
                salt = PasswordGenerator.getInstance().generateSalt(password);
                password = PasswordGenerator.getInstance().generatePassword(password, salt);
                user = UserServiceImpl.getInstance().isAuthorized(username, password, salt);
                if (user != null) {
                    req.getSession().setAttribute(Parameters.ROLE, user.getRole());
                    req.getSession().setAttribute(Parameters.USERNAME, user.getUsername());
                    return null;
                }
            } catch (ServiceException | ToolException e) {
                SystemLogger.getInstance().logError(getClass(), e.getMessage());
            }
        }
        req.setAttribute(Parameters.ERROR_LOGIN, MessageManager.getInstance().getProperty(MessageConstants.WRONG_LOGIN));
        page = ConfigurationManager.getProperty(PageConstants.LOGIN);
        return page;
    }
}
