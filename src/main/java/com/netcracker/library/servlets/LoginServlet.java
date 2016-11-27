package com.netcracker.library.servlets;

import com.netcracker.library.commands.Command;
import com.netcracker.library.commands.CommandFactory;
import com.netcracker.library.commands.CommandType;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.exceptions.CommandException;
import com.netcracker.library.tools.ConfigurationManager;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        Command command;
        String page = ConfigurationManager.getProperty(PageConstants.LOGIN);
        try {
            command = CommandFactory.getInstance().defineCommand(CommandType.LOGIN);
            page = command.execute(req);
            if (page == null){
                resp.sendRedirect(RedirectConstants.INDEX);
                return;
            }
        } catch (CommandException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
