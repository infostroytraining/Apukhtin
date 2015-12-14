package com.apukhtin.dao.postgre;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.dao.postgre.transaction.TransactionManager;
import com.apukhtin.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.apukhtin.dao.postgre.transaction.TransactionManager.doTransaction;

/**
 * Created by Vlad on 13.12.2015.
 */
public class PostrgeUserDAO implements UserDAO {

    private final static String SELECT_ALL_QUERY = "SELECT * FROM \"user\"";
    TransactionManager transactionManager = new TransactionManager();

    @Override
    public boolean emailExists(String email) {
        return false;
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new LinkedList<>();
        try {
            ResultSet resultSet = doTransaction((connection -> {
                return connection.createStatement().executeQuery(SELECT_ALL_QUERY);
            }));

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("password"),
                        resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return users;
    }

    @Override
    public User add(User obj) {
        return null;
    }

    @Override
    public boolean remove(User obj) {
        return false;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public boolean contains(long id) {
        return false;
    }
}
