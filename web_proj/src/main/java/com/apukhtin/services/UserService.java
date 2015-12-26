package com.apukhtin.services;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private static Logger logger = LogManager.getLogger();

    private static UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public static Map<String, String> validateUserValues(User user) throws ServiceException {
        logger.debug("Start validating");
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

        result.putAll(validateForExistingEmail(user));
        return result;
    }

    public static Map<String, String> validateForExistingEmail(User user) throws ServiceException {
        Map<String, String> errMap = new HashMap<>();
        try {
            if (dao.emailExists(user.getEmail())) {
                errMap.put("email", "User with such email already exists");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return errMap;
    }

    public void addUser(User user) throws ServiceException {
        logger.entry(user);
        Map<String, String> errors = validateUserValues(user);
        if (!errors.isEmpty()) {
            String errorMsg = errors.entrySet()
                    .stream()
                    .map(entry -> entry.getValue())
                    .collect(Collectors.joining("\n"));

            throw new ServiceException(errorMsg);
        }

        try {
            if (dao.emailExists(user.getEmail())) {
                logger.warn("User with email " + user.getEmail() + " already exists");
                throw new ServiceException("User with such email exists");
            }
            dao.add(user);
            logger.info("User successful added", user);
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
