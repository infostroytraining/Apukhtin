package com.apukhtin.dao.postgre.transaction;

import com.apukhtin.dao.connection.ConnectionEstablisher;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vlad on 14.12.2015.
 */
public class TransactionManager {
    public static <T> T doTransaction(Transaction<T> transaction) throws SQLException {

        Connection connection = null;
        T result = null;

        //doing try/catch only for finally statement to be executed whatever would happen
        try {
            connection = ConnectionEstablisher.getConnection();
            connection.setAutoCommit(false);
            result = transaction.execute(connection);
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
        return result;
    }
}
