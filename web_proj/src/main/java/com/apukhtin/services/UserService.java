package com.apukhtin.services;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.dao.memory.InMemoryUserDaoImpl;
import com.apukhtin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

public class UserService {

    private static Logger logger = LogManager.getLogger();

    //инициализируй ДАО в листенере через конструктор. Сервис не должен быть ответсвенным за создание своих зависимостей!!
    private UserDAO dao = new InMemoryUserDaoImpl();

   // Этот метод выглядит немного нелогичным.  Во первых, при твоей логике избыточна переменная isError. Так как можно проверить 
   //descr.isEmpty() и этого будет достаточно. Во вторых, у тебя всегда учитывается только одна ошибка. А если их будет несколько? 
   //Почему бы не использовать подход со списком(картой) ошибок?
    /ublic static void validateForNulls(User user) throws ServiceException {
        logger.entry(user);
        boolean isError = false;
        String descr = "";

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            isError = true;
            descr = "No email given";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            isError = true;
            descr = "No pass given";
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            isError = true;
            descr = "No name given";
        }

        if (isError) {
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
}
