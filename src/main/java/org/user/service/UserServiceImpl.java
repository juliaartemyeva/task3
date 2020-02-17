package org.user.service;

import org.user.dao.UserDAO;
import org.user.model.User;
import org.user.util.UserDAOFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService = new UserServiceImpl();
    private UserDAO dao = new UserDAOFactory().getDAO();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return userService;
    }

    @Override
    public void insertUser(User user) {
        dao.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return dao.selectUser(id);
    }

    @Override
    public List <User> selectAllUsers() {
        return dao.selectAllUsers();
    }

    @Override
    public void deleteUser(int id) {
        dao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
