package com.apukhtin.dao.memory;

import com.apukhtin.model.User;

import java.util.LinkedList;

/**
 * Class that contains users in memory. Aka DB.
 * Singleton: one "connection" per app.
 */
class UserContainer {
    private static UserContainer instance = null;
    private LinkedList<User> users = new LinkedList<>();

    {
        users.add(new User("Vlad Apukhtin", "123", "pandalmail@gmail.com") {
            {
                setId(1);
            }
        });
        users.add(new User("Gena Ivanov", "123", "gena@gmail.com") {
            {
                setId(2);
            }
        });
        users.add(new User("Sasha Grinenko", "321", "sasha@gmail.com") {
            {
                setId(3);
            }
        });
    }

    private UserContainer() {
    }

    public static synchronized UserContainer getInstance() {
        if (instance == null) instance = new UserContainer();

        return instance;
    }

    public LinkedList<User> getUsers() {
        return users;
    }
}
