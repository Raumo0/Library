package com.netcracker.library.commands.library;

import com.netcracker.library.commands.Command;
import com.netcracker.library.constants.PageConstants;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.tools.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 27.11.16.
 */
public class ContactCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        //todo add more data
        String page;
        req.setAttribute(Parameters.NAVBAR_ACTIVE_CONTACT, Parameters.NAVBAR_ACTIVE);
        page = ConfigurationManager.getProperty(PageConstants.CONTACT);
        return page;
    }
}