package com.apukhtin.servlets;

import com.apukhtin.model.User;
import com.apukhtin.services.ServiceException;
import com.apukhtin.services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class Register extends javax.servlet.http.HttpServlet {

    private Logger logger = LogManager.getLogger();
    private static Type MAP_TYPE = new TypeToken<Map<Integer, String>>() {
    }.getType();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        User u = getUserFromRequest(request);
        logger.debug("User input to register servlet. Got: ", u);

        UserService userService = ((UserService) request.getServletContext().getAttribute("userService"));
        logger.debug("Accessed to context to UserService. Got: ", userService);

        try {
            // validation
            logger.debug("Validating user");
            Map<String, String> possibleErrors = UserService.validateUserValues(u);
            if (possibleErrors.isEmpty()) { // in case it passed
                logger.debug("No errors found. Validation complete");

                userService.addUser(u);

                logger.debug("Forwarding to users.jsp");
                request.setAttribute("msg", "User has been added");
                request.getRequestDispatcher("users.jsp").forward(request, response);
            } else {
                logger.warn("Validation has not been passed. Errors: ", possibleErrors.toString());
                appendErr(response, gson.toJson(possibleErrors, MAP_TYPE));
            }
        } catch (ServiceException e) {
            logger.error("Exception from service caught. ");
            logger.catching(e);
            JsonObject errJson = new JsonObject();
            errJson.addProperty("initErr", e.getMessage());
            appendErr(response, errJson.getAsString());
        }
    }

    private void append(HttpServletResponse response, String err) throws IOException {
        response.setHeader("Content-Type", "application/json");
        response.getWriter().append(err);
    }

    private void appendSuccess(HttpServletResponse response, String err) throws IOException {
        response.setStatus(200);
        append(response, err);
    }

    private void appendErr(HttpServletResponse response, String err) throws IOException {
        response.setStatus(400);
        append(response, err);
    }

    private User getUserFromRequest(HttpServletRequest request) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        User u = new User(name, password, email);

        return u;
    }
}
