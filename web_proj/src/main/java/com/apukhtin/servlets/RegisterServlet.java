package com.apukhtin.servlets;

import com.apukhtin.dao.postgre.PostrgeUserDAO;
import com.apukhtin.model.User;
import com.apukhtin.services.ServiceException;
import com.apukhtin.services.UserService;
import com.apukhtin.services.UserValidator;
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

public class RegisterServlet extends javax.servlet.http.HttpServlet {

    private Logger logger = LogManager.getLogger();
    private static Type MAP_TYPE = new TypeToken<Map<Integer, String>>() {
    }.getType();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        User u = getUserFromRequest(request);
        logger.debug("User input to register servlet. Got: ", u);

        UserService userService = ((UserService) request.getServletContext().getAttribute("userService"));
        PostrgeUserDAO dao = (PostrgeUserDAO) request.getServletContext().getAttribute("postgreDao");
        logger.debug("Accessed to context to UserService. Got: ", userService);


        try {
            // validation
            logger.debug("Validating user");
            Map<String, String> possibleErrors = UserValidator.validateUserValues(u, dao);
            if (possibleErrors.isEmpty()) { // in case it passed
                logger.debug("No errors found. Validation complete");

                userService.addUser(u);

                logger.debug("Forwarding to users.jsp");
            } else {
                logger.warn("Validation has not been passed. Errors: ", possibleErrors.toString());
                appendErr(response, gson.toJson(possibleErrors, MAP_TYPE));
            }
        } catch (ServiceException e) {
            logger.error("Exception from service caught. ");
            logger.catching(e);
            JsonObject errJson = new JsonObject();
            errJson.addProperty("initErr", e.getMessage());
            appendErr(response, gson.toJson(errJson));
        }
    }

    private void append(HttpServletResponse response, String err) throws IOException {
        if(response == null) return;
        response.setHeader("Content-Type", "application/json");
        response.getWriter().append(err);
    }

    private void appendErr(HttpServletResponse response, String err) throws IOException {
        if(response == null) return;
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
