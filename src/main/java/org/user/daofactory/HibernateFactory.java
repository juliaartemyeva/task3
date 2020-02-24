package org.user.daofactory;

import org.user.dao.UserDao;
import org.user.dao.UserDaoHibernateImpl;

public class HibernateFactory extends DaoFactory {
    @Override
    public UserDao createDAO() {
        return UserDaoHibernateImpl.getInstance();
    }
}