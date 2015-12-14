package com.apukhtin.servlets;

import com.apukhtin.model.User;
import com.apukhtin.services.ServiceException;
import com.apukhtin.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User u = getUserFromRequest(request);
        UserService userService = new UserService();
        try {
            userService.addUser(u);
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        request.setAttribute("msg", "User has been added");
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    private User getUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        User u = new User(name, password, email);

        return u;
    }
}
