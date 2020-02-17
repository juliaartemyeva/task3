package org.user.daofactory;

import org.user.dao.UserDao;
import org.user.model.User;
import org.user.service.UserService;

import java.util.List;

public class FactoryHelper implements UserService {
    private UserDao userDAO;

    public FactoryHelper(DaoFactory daoFactory) {
        userDAO = daoFactory.createDAO();
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return userDAO.selectUser(id);
    }

    @Override
    public List <User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}