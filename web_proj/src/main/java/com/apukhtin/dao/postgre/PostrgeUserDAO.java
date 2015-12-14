package com.apukhtin.dao.postgre;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.UserDAO;
import com.apukhtin.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static com.apukhtin.dao.postgre.transaction.TransactionManager.doTransaction;

/**
 * Created by Vlad on 13.12.2015.
 */
public class PostrgeUserDAO implements UserDAO {

    private final static String SELECT_ALL_QUERY = "SELECT * FROM \"user\"";
    private final static String ADD_QUERY = "INSERT into \"user\"(name, email, password)  VALUES (?,?,?)"; // ? name, ? email, ? password

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
    public User add(User obj) throws DAOException {
        try {
            int newId = doTransaction(connection -> {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setString(3, obj.getPassword());

                preparedStatement.executeUpdate();

                int Id = 0;
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Id = generatedKeys.getInt(1);
                }
                return Id;
            });

            obj.setId(newId);
            return obj;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
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
