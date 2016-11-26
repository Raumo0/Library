package com.netcracker.library.servlets;

import com.netcracker.library.commands.Command;
import com.netcracker.library.commands.CommandFactory;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.tools.ConfigurationManager;

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
        String page = null;
        CommandFactory client = CommandFactory.getInstance();
        Command command = client.defineCommand(request);
        if (command != null)
            page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(PageConstants.INDEX);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
//            response.sendRedirect("/");
        }
    }
}
