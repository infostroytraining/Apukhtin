package com.apukhtin.dao.postgre;

import com.apukhtin.connection.ConnectionEstablisher;
import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.LogDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vlad on 19.12.2015.
 */
public class PostgreLogDAO implements LogDAO {

    private static String ADD_LOG_QUERY = "insert into logs(message) values(?)";
    private static String GET_MESSAGES_QUERY = "select message from logs";

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ignore) {
        }
    }

    private static void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {

        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {

        }
    }

    @Override
    public void log(String msg) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionEstablisher.getConnection();
            statement = connection.prepareStatement(ADD_LOG_QUERY);
            statement.setString(1, msg);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }

    @Override
    public List<String> getLogs() throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            List<String> messages = new LinkedList<>();
            connection = ConnectionEstablisher.getConnection();
            resultSet = connection.createStatement().executeQuery(GET_MESSAGES_QUERY);

            while (resultSet.next()) {
                String msg = resultSet.getString("message");
                messages.add(msg);
            }

            return messages;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
    }
}
