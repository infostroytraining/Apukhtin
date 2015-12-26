package com.apukhtin.services;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private static Logger logger = LogManager.getLogger();

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public static Map<String, String> validateUserValues(User user) throws ServiceException {
        logger.entry(user);
        Map<String, String> result = new HashMap<>();

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            result.put("email", "No email given");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            result.put("password", "No password given");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            result.put("name", "No name given");
        }

        return result;
    }

    public void addUser(User user) throws ServiceException {
        logger.entry(user);
        Map<String, String> errors = validateUserValues(user);
        if(!errors.isEmpty()) throw new ServiceException(errors.toString());

        try {
            if (dao.emailExists(user.getEmail())) {
                logger.warn("User with email " + user.getEmail() + " already exists");
                throw new ServiceException("User with such email exists");
            }
            dao.add(user);
            logger.info("User succesfull added", user);
        } catch (DAOException e) {
            logger.catching(e);
            throw new ServiceException(e);
        }

        logger.exit();
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> getUsers() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
