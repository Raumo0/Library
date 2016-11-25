package com.netcracker.library.filters;

import com.netcracker.library.enums.UserRole;
import com.netcracker.library.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by raumo0 on 25.11.16.
 */
@WebFilter(urlPatterns = {"/login/*", "/registration/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        UserRole type = (UserRole) session.getAttribute("role");
        if (type != null) {
            String page = ConfigurationManager.getProperty("path.page.index");
            response.sendRedirect("/");
            return;
        }
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
