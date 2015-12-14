package dao.connection;

import dao.connection.configer.ConnectionConfig;
import dao.connection.configer.StringConfigManager;
import dao.connection.configer.exception.ConfigException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vlad on 14.12.2015.
 */
public class ConnectionEstablisher {
    private static ConnectionConfig connectionConfig;

    public static Connection getConnection() throws SQLException {
        StringConfigManager manager =
                new StringConfigManager("jdbc:postgresql://127.0.0.1:5432/mydb", "postgres", "123456");

        Connection connection = null;
        try {
            ConnectionConfig config = manager.getConfig();
            loadPostrgeDriver();
            connection =
                    DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        } catch (ConfigException e) {
            throw new SQLException("Config parametres exception. Details:\n" + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Cannot load driver");
        }

        return connection;
    }

    private static void loadPostrgeDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }
}
