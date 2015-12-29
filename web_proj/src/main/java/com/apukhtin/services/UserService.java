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

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public void addUser(User user) throws ServiceException {
        logger.entry(user);
        Map<String, String> errors = UserValidator.validateUserValues(user, dao);
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
