package com.apukhtin.services;

import com.apukhtin.dao.UserDAO;
import com.apukhtin.dao.memory.InMemoryUserDaoImpl;
import com.apukhtin.model.User;

public class UserService {

    private UserDAO dao = new InMemoryUserDaoImpl();

    public static void validateForNulls(User user) throws IllegalArgumentException {
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
            throw new IllegalArgumentException(descr);
        }
    }

    public void addUser(User user) throws IllegalArgumentException {
        validateForNulls(user);

        if (dao.emailExists(user.getEmail())) {
            throw new IllegalArgumentException("User with such email exists");
        }

        dao.add(user);
    }
}
