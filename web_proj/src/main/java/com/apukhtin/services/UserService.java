package com.apukhtin.services;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.dao.memory.InMemoryUserDaoImpl;
import com.apukhtin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

import java.util.List;

public class UserService {

    private static Logger logger = LogManager.getLogger();

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public static void validateForNulls(User user) throws ServiceException {
        logger.entry(user);
        String descr = "";

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            descr += "No email given";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            descr += "No pass given";
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            descr += "No name given";
        }

        if (descr.isEmpty()) {
            logger.debug("Not validated. " + descr);
            throw new ServiceException(descr);
        }
    }

    public void addUser(User user) throws ServiceException {
        logger.entry(user);
        validateForNulls(user);

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
