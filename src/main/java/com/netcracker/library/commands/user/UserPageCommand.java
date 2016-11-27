package com.netcracker.library.commands.user;

import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 27.11.16.
 */
public class UserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute(Parameters.USER_ID, req.getSession().getAttribute(Parameters.USER_ID));
        return ConfigurationManager.getProperty(PageConstants.PROFILE);
    }
}
