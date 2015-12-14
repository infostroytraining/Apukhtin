package dao.postgre;

import dao.UserDAO;
import model.User;

import java.util.List;

/**
 * Created by Vlad on 13.12.2015.
 */
public class PostrgeUserDAO implements UserDAO {
    @Override
    public boolean emailExists(String email) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User add(User obj) {
        return null;
    }

    @Override
    public boolean remove(User obj) {
        return false;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public boolean contains(long id) {
        return false;
    }
}
