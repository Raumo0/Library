package com.netcracker.library.servlets;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.resource.ConfigurationManager;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.PasswordGenerator;
import com.netcracker.library.tools.SystemLogger;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String page = ConfigurationManager.getProperty("path.page.login");
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String salt;
        User user;
        if (!(username.isEmpty() || password.isEmpty() ||
                username.length() > 45 || password.length() > 45)) {
            try {
                salt = PasswordGenerator.getInstance().generateSalt(password);
                password = PasswordGenerator.getInstance().generatePassword(password, salt);
                user = UserServiceImpl.getInstance().isAuthorized(username, password, salt);
                if (user != null) {
                    req.getSession().setAttribute("role", user.getRole());
                    req.getSession().setAttribute("username", user.getUsername());
                    String page = ConfigurationManager.getProperty("path.page.index");
//                    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
//                    dispatcher.forward(req, resp);
//                    return;
                    resp.sendRedirect("/");
                    return;
                }
            } catch (DAOException e) {
                SystemLogger.getInstance().logError(getClass(), e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                SystemLogger.getInstance().logError(getClass(), e.getMessage());
            }
        }
        String page = ConfigurationManager.getProperty("path.page.login");
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
