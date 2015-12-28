package com.apukhtin.dao.connection;

import com.apukhtin.dao.connection.configer.ConnectionConfig;
import com.apukhtin.dao.connection.configer.StringConfigManager;
import com.apukhtin.dao.connection.configer.exception.ConfigException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vlad on 14.12.2015.
 */
public class ConnectionEstablisher {
    private static ConnectionConfig connectionConfig;
    private static Logger logger = LogManager.getLogger();

    public static Connection getConnection() throws SQLException {
        StringConfigManager manager =
                new StringConfigManager("jdbc:postgresql://127.0.0.1:5432/mydb?characterEncoding=utf8", "postgres", "123456");

        Connection connection = null;
        try {
            ConnectionConfig config = manager.getConfig();
            logger.debug("Config loaded");
            logger.debug("Try to log driver");
            loadPostrgeDriver();
            logger.debug("Driver loaded");
            connection =
                    DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        } catch (ConfigException e) {
            logger.catching(e);
            throw new SQLException("Config parametres exception. Details:\n" + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.catching(e);
            throw new SQLException("Cannot load driver");
        }

        return connection;
    }

    private static void loadPostrgeDriver() throws ClassNotFoundException {
        logger.debug("Trying to load driver org.postgresql.Driver");
        Class.forName("org.postgresql.Driver");
        logger.debug("Driver succesfully loaded");
    }
}
