package com.apukhtin.dao;

import com.apukhtin.model.User;

import java.util.List;


public interface UserDAO extends DAO<User> {
    boolean emailExists(String email);

    List<User> getAll();
}
