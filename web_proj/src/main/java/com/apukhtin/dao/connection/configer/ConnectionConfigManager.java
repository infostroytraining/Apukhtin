package com.apukhtin.dao.connection.configer;

import com.apukhtin.dao.connection.configer.exception.ConfigException;

/**
 * Created by Vlad on 14.12.2015.
 */

/**
 * Class that has been created to be able to load db configurations from many sources
 * and validate the input
 */
public abstract class ConnectionConfigManager {
    protected ConnectionConfig connectionConfig;

    public ConnectionConfigManager() {
        connectionConfig = new ConnectionConfig();
    }

    public ConnectionConfig getConfig() throws ConfigException {
        validate(connectionConfig);
        return connectionConfig;
    }

    private void validate(ConnectionConfig connectionConfig) throws ConfigException {
        if (connectionConfig.getPassword() == null || connectionConfig.getPassword().isEmpty())
            throw new ConfigException("Config password in not provided (null or empty)");
        if (connectionConfig.getUrl() == null || connectionConfig.getUrl().isEmpty())
            throw new ConfigException("DB url is not provided");
        if (connectionConfig.getUser() == null || connectionConfig.getUser().isEmpty())
            throw new ConfigException("Username is not provided");
    }
}
