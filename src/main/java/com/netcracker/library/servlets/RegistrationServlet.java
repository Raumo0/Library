package com.netcracker.library.servlets;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.SystemLogger;
import com.netcracker.library.tools.ConfigurationManager;
import com.netcracker.library.tools.PasswordGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by raumo0 on 24.11.16.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String page = ConfigurationManager.getProperty(PageConstants.REGISTRATION);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter(Parameters.FIRST_NAME));
        user.setLastName(req.getParameter(Parameters.LAST_NAME));
        user.setUsername(req.getParameter(Parameters.USERNAME));
        user.setPassword(req.getParameter(Parameters.PASSWORD));
        user.setRole(UserRole.READER);
        try {
            if (!inspection(user)) {
                String page = ConfigurationManager.getProperty(PageConstants.REGISTRATION);
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                dispatcher.forward(req, resp);
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        try {
            user.setSalt(PasswordGenerator.getInstance().generateSalt(user.getPassword()));
            user.setPassword(PasswordGenerator.getInstance().generatePassword(user.getPassword(), user.getSalt()));
            user.setId(UserServiceImpl.getInstance().addUser(user));

            req.getSession().setAttribute(Parameters.ROLE, user.getRole());
            req.getSession().setAttribute(Parameters.USERNAME, user.getUsername());
            resp.sendRedirect(RedirectConstants.INDEX);
            return;
        } catch (ServiceException | NoSuchAlgorithmException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        resp.sendRedirect(RedirectConstants.REGISTRATION);
    }

    private boolean inspection(User user) throws NoSuchAlgorithmException {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getUsername().isEmpty()
                || user.getPassword().isEmpty())
            return false;
        if (user.getFirstName().length() > 45 || user.getLastName().length() > 45 || user.getUsername().length() > 45
                || user.getPassword().length() > 45)
            return false;
        return true;
    }
}
