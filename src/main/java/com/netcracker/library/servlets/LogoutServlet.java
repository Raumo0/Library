package com.netcracker.library.servlets;

import com.netcracker.library.commands.Command;
import com.netcracker.library.commands.CommandFactory;
import com.netcracker.library.commands.CommandType;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.exceptions.CommandException;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by raumo0 on 26.11.16.
 */
@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Command command = CommandFactory.getInstance().defineCommand(CommandType.LOGOUT);
            command.execute(req);
        } catch (CommandException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
            return;
        }
        resp.sendRedirect(RedirectConstants.INDEX);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
