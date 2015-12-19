package com.apukhtin.filters;

import com.apukhtin.dao.memory.InMemoryUserDaoImpl;
import com.apukhtin.model.User;
import com.apukhtin.services.UserService;

import javax.servlet.*;
import java.io.IOException;


public class UsersViewFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        UserService userService = ((UserService) req.getServletContext().getAttribute("userService"));
        req.getServletContext().setAttribute("users", userService);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
