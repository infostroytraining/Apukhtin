package dao;

import model.User;

import java.util.List;

public class InMemoryUserDaoImpl implements UserDAO {

    private UserContainer userDB = UserContainer.getInstance();

    @Override
    public User add(User obj) {
        if (userDB.getUsers().contains(obj)) {
            return null;
        }

        // search user index
        if (obj.getId() == 0) {
            int index = userDB.getUsers().size();

            // if index of length
            while (contains(index)) {
                index++;
            }
            obj.setId(index);
        }

        userDB.getUsers().add(obj);

        return obj;
    }

    @Override
    public boolean remove(User obj) {
        if (!contains(obj.getId())) {
            return false;
        }

        userDB.getUsers().remove(obj);

        return true;
    }

    /**
     * Method that returns user due to id or null, if no such is provided
     *
     * @param id
     * @return User, if exists, otherwise null
     */
    @Override
    public User get(long id) {
        for (User u : userDB.getUsers()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void update(User obj) {
        if (contains(obj.getId())) {
            List<User> userList = userDB.getUsers();

            for (User u : userList) {
                if (u.getId() == obj.getId()) {
                    userList.remove(u);
                    userList.add(obj);

                    break;
                }
            }
        }
    }

    // Equality is provided due to id
    @Override
    public boolean contains(long id) {
        for (User u : userDB.getUsers()) {
            if (u.getId() == id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean emailExists(String email) {
        return userDB.getUsers().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    @Override
    public List<User> getAll() {
        return userDB.getUsers();
    }
}
