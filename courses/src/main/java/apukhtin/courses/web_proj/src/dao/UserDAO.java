package dao;

import model.User;


public interface UserDAO extends DAO<User> {
    boolean emailExists(String email);
}
