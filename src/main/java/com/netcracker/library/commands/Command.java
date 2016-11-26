package com.netcracker.library.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 24.11.16.
 */
public interface Command {
    String execute(HttpServletRequest req);
}
