package com.apukhtin.dao;

import com.apukhtin.dao.postgre.PostgreLogDAO;

import java.sql.SQLException;

/**
 * Created by vlad on 19.12.2015.
 */
public class Main {
    public static void main(String[] args) throws SQLException, DAOException {
        new PostgreLogDAO().log("hello");
    }
}
