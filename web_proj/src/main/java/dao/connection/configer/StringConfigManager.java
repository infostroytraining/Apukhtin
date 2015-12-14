package dao.connection.configer;

/**
 * Created by Vlad on 14.12.2015.
 */
public class StringConfigManager extends ConnectionConfigManager {

    public StringConfigManager(String url, String user, String password) {
        connectionConfig.setUrl(url);
        connectionConfig.setUser(user);
        connectionConfig.setPassword(password);
    }
}