package org.user.util;

import org.user.dao.UserDAO;
import org.user.dao.UserDAOHibernate;
import org.user.dao.UserDAOJDBC;

import java.io.IOException;
import java.util.Properties;

public class UserDAOFactory {

    public UserDAO getDAO() {
        UserDAO userDao = null;
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String daoType = properties.getProperty("dao");
        switch (daoType) {
            case ("JDBC"):
                userDao = UserDAOJDBC.getInstance();
            case ("Hibernate"):
                userDao = UserDAOHibernate.getInstance();
        }
        return userDao;
    }
}

