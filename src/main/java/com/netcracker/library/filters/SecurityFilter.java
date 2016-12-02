package com.netcracker.library.filters;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.constants.Parameters;
import com.netcracker.library.constants.RedirectConstants;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.services.impl.UserServiceImpl;
import com.netcracker.library.tools.SystemLogger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by raumo0 on 24.11.16.
 */
@WebFilter(urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        User user;
        UserRole type = null;
        int userId = 0;
        try {
            userId = (int) session.getAttribute(Parameters.USER_ID);
            user = UserServiceImpl.getInstance().getUserById(userId);
            type = user.getRole();
        } catch (ServiceException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        } catch (NullPointerException e) {
        }
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (type == null && !(path.startsWith(RedirectConstants.LOGIN) ||
                path.startsWith(RedirectConstants.REGISTRATION) || path.startsWith(RedirectConstants.RESOURCES))){
            response.sendRedirect(RedirectConstants.LOGIN);
            return;
        }
        if (path.equals(RedirectConstants.INDEX)){
            request.setAttribute(Parameters.PAGE_TITLE, "Library");
            request.setAttribute(Parameters.NAVBAR_ACTIVE_HOME, Parameters.NAVBAR_ACTIVE);
        }
        request.setAttribute(Parameters.USER_ID, userId);
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
