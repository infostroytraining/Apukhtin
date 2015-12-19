package com.apukhtin.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vlad on 19.12.2015.
 */
public class ConnectionEstablisher {
    public static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    private static void loadDriver() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
}
