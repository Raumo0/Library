package com.netcracker.library.filters;

import com.netcracker.library.enums.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by raumo0 on 24.11.16.
 */
//@WebFilter(urlPatterns = { "/s", "/controller" }, servletNames = { "MyServlet" })
//@WebFilter(urlPatterns = { "/" }, servletNames = { "MyServlet" })
@WebFilter(servletNames = { "/MyServlet", "/login", "LoginServlet" })
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


//        UserRole type = (UserRole) session.getAttribute("role");
//        if (type == null) {
////            type = UserRole.GUEST;
////            session.setAttribute("role", type);
//            String page = ConfigurationManager.getProperty("path.page.login");
//            RequestDispatcher dispatcher = ((HttpServletRequest) servletRequest).getSession().getServletContext().
//                    getRequestDispatcher(page);
//            dispatcher.forward(request, response);
//
////            response.sendRedirect(request.getContextPath() + page);
//            return;
//        }
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
