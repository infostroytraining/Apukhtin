package com.apukhtin.filters;

import javax.servlet.*;
import java.io.IOException;


public class UsersViewFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

//        req.getServletContext().setAttribute("users", new InMemoryUserDaoImpl().getAll());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
