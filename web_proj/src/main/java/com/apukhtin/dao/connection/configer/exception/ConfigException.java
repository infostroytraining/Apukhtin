package com.apukhtin.dao.connection.configer.exception;

/**
 * Created by Vlad on 14.12.2015.
 */
public class ConfigException extends Exception {
    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException() {
    }
}
