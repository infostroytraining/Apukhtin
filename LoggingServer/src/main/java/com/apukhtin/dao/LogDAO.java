package com.apukhtin.dao;

import java.util.List;

/**
 * Created by vlad on 19.12.2015.
 */
public interface LogDAO {
    void log(String msg) throws DAOException;

    List<String> getLogs() throws DAOException;
}
