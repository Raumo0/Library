package com.netcracker.library.servlets;

import com.netcracker.library.commands.Command;
import com.netcracker.library.commands.CommandFactory;
import com.netcracker.library.commands.CommandType;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.exceptions.CommandException;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by raumo0 on 27.11.16.
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        Command command;
        RequestDispatcher dispatcher;
        try {
            command = CommandFactory.getInstance().defineCommand(CommandType.USER_PAGE);
            page = command.execute(req);
        } catch (CommandException | NullPointerException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
        if (page == null) {
            resp.sendRedirect(RedirectConstants.INDEX);
            return;
        }
        dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
