package com.netcracker.library.servlets;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.constants.MessageConstants;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.tools.ConfigurationManager;
import com.netcracker.library.service.impl.UserServiceImpl;
import com.netcracker.library.tools.MessageManager;
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
        String page = ConfigurationManager.getProperty(PageConstants.LOGIN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Parameters.USERNAME);
        String password = req.getParameter(Parameters.PASSWORD);
        String salt;
        User user;
        if (!(username.isEmpty() || password.isEmpty() ||
                username.length() > 45 || password.length() > 45)) {
            try {
                salt = PasswordGenerator.getInstance().generateSalt(password);
                password = PasswordGenerator.getInstance().generatePassword(password, salt);
                user = UserServiceImpl.getInstance().isAuthorized(username, password, salt);
                if (user != null) {
                    req.getSession().setAttribute(Parameters.ROLE, user.getRole());
                    req.getSession().setAttribute(Parameters.USERNAME, user.getUsername());
                    resp.sendRedirect(RedirectConstants.INDEX);
                    return;
                }
            } catch (DAOException | NoSuchAlgorithmException e) {
                SystemLogger.getInstance().logError(getClass(), e.getMessage());
            }
        }
        req.setAttribute(Parameters.ERROR_LOGIN, MessageManager.getInstance().getProperty(MessageConstants.WRONG_LOGIN));
        String page = ConfigurationManager.getProperty(PageConstants.LOGIN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
