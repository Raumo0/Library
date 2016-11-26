package com.netcracker.library.commands.user;

import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 24.11.16.
 */
public class LogoutReaderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PageConstants.INDEX);
        request.getSession().invalidate();
        return page;
    }
}
