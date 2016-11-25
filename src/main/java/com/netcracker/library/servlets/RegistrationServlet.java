package com.netcracker.library.servlets;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.SystemLogger;
import com.netcracker.library.resource.ConfigurationManager;
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
        String page = ConfigurationManager.getProperty("path.page.registration");
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setSalt("salt");
        user.setRole(UserRole.READER);
        try {
            if (!inspection(user))//todo
                return;
        } catch (NoSuchAlgorithmException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        try {
            user.setSalt(PasswordGenerator.getInstance().generateSalt(user.getPassword()));
            user.setPassword(PasswordGenerator.getInstance().generatePassword(user.getPassword(), user.getSalt()));
            user.setId(UserServiceImpl.getInstance().addUser(user));

            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("username", user.getUsername());
//            String page = ConfigurationManager.getProperty("path.page.index");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
//            dispatcher.forward(req, resp);
            resp.sendRedirect("/");
            return;
        } catch (DAOException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        String page = ConfigurationManager.getProperty("path.page.registration");
//        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
//        dispatcher.forward(req, resp);
        resp.sendRedirect("/registration");
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
