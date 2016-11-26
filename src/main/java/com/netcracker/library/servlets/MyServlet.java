package com.netcracker.library.servlets;

import com.netcracker.library.commands.Command;
import com.netcracker.library.commands.CommandFactory;
import com.netcracker.library.constants.PageConstants;
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
 * Created by raumo0 on 18.11.16.
 */
@WebServlet("/panel")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        Command command;
        RequestDispatcher dispatcher;
        CommandFactory client = CommandFactory.getInstance();
        try {
            command = client.defineCommand(request);
            page = command.execute(request);
            dispatcher = getServletContext().getRequestDispatcher(page);
        } catch (CommandException | NullPointerException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
            page = ConfigurationManager.getProperty(PageConstants.INDEX);
            dispatcher = getServletContext().getRequestDispatcher(page);
        }
        dispatcher.forward(request, response);
    }
}
