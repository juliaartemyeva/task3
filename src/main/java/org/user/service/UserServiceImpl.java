package org.user.service;

import org.user.daofactory.DAOFactory;
import org.user.daofactory.FactoryHelper;
import org.user.daofactory.HibernateFactory;
import org.user.daofactory.JDBCFactory;
import org.user.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService = new UserServiceImpl();
    FactoryHelper dao = configureApplication();

    private FactoryHelper configureApplication() {
        FactoryHelper dao;
        DAOFactory factory;
        if (getDaoName().equalsIgnoreCase("Hibernate")) {
            factory = new HibernateFactory();
        } else {
            factory = new JDBCFactory();
        }
        dao = new FactoryHelper(factory);
        return dao;
    }

    private String getDaoName() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("dao");
    }

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
