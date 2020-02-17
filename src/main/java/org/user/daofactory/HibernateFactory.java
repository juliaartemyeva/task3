package org.user.daofactory;

import org.user.dao.UserDAO;
import org.user.dao.UserDAOHibernate;

public class HibernateFactory implements DAOFactory {
    @Override
    public UserDAO createDAO() {
        return UserDAOHibernate.getInstance();
    }
}