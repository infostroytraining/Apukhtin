package com.apukhtin.dao.postgre.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vlad on 14.12.2015.
 */
public interface Transaction<T> {
    T execute(Connection connection) throws SQLException;
}
