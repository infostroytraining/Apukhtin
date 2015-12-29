package com.apukhtin.services;

import com.apukhtin.dao.DAO;
import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 29.12.2015.
 */
public class UserValidator {

    private static Logger logger = LogManager.getLogger();

    public static Map<String, String> validateUserValues(User user, UserDAO dao) throws ServiceException {
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

        result.putAll(validateForExistingEmail(user, dao));
        return result;
    }

    public static Map<String, String> validateForExistingEmail(User user, UserDAO dao) throws ServiceException {
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
}
