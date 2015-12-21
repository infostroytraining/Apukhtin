package com.apukhtin.controllers;

import com.apukhtin.services.LogService;
import com.apukhtin.services.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by vlad on 21.12.2015.
 */
public class LogServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LogService service = (LogService) request.getServletContext().getAttribute("logService");

        try {
            service.log(request.getParameter("logEvent"));
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        LogService service = (LogService) request.getServletContext().getAttribute("logService");

        try {
            request.setAttribute("logs", service.getLogs());
            request.getRequestDispatcher("logs.jsp").forward(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
