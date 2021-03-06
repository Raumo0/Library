package com.netcracker.library.commands.user;

import com.netcracker.library.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 24.11.16.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return null;
    }
}
