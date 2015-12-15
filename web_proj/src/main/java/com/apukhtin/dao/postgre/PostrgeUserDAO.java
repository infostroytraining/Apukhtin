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
    private final static String CONTAINS_QUERY = "SELECT * FROM \"user\" WHERE id=?";
    private final static String UPDATE_QUERY = "UPDATE \"user\" SET email=?, name=?, password=? " +
            "WHERE id = ?";
    private final static String GET_QUERY = "SELECT * FROM \"user\" WHERE id=?";
    private final static String REMOVE_QUERY = "DELETE FROM \"user\" WHERE id=?";
    private final static String EMAIL_EXIST_QUERY = "SELECT * from \"user\" WHERE email=?";
    @Override
    public boolean emailExists(String email) throws DAOException {
        boolean result = false;
        try {
            result = doTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement(EMAIL_EXIST_QUERY);
                statement.setString(1, email);

                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            });
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return result;
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
    public boolean remove(User obj) throws DAOException {
        if (!contains(obj.getId())) return false;
        try {
            return doTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY);
                statement.setLong(1, obj.getId());

                return statement.executeUpdate() > 0;
            });
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    /**
     * Returns user if db contains it with such id, null otherwise
     *
     * @param id
     * @return
     */
    @Override
    public User get(long id) throws DAOException {
        User user = null;
        try {
            if (contains(id)) {
                ResultSet resultSet = doTransaction(connection -> {
                    PreparedStatement statement = connection.prepareStatement(GET_QUERY);
                    statement.setLong(1, id);
                    return statement.executeQuery();
                });
                if (resultSet.next()) {
                    user = new User();

                    user.setId(id);
                    user.setEmail(resultSet.getString("email"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public void update(User obj) throws DAOException {
        try {
            if (contains(obj.getId())) {
                doTransaction(connection -> {
                    PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
                    statement.setString(1, obj.getEmail());
                    statement.setString(2, obj.getName());
                    statement.setString(3, obj.getPassword());

                    statement.setLong(4, obj.getId());
                    return statement.executeUpdate();
                });
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean contains(long id) throws DAOException {
        boolean result = false;
        try {
            ResultSet resultSet = doTransaction(connection -> {
                PreparedStatement statement = connection.prepareStatement(CONTAINS_QUERY);
                statement.setLong(1, id);
                return statement.executeQuery();
            });
            result = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return result;
    }
}
