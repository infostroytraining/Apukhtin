package dao;

import model.User;

import java.util.List;


public interface UserDAO extends DAO<User> {
    boolean emailExists(String email);

    List<User> getAll();
}
