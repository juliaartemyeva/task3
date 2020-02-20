package org.user.daofactory;

import org.user.dao.UserDao;
import org.user.dao.UserDaoJDBCImpl;

public class JDBCFactory implements DaoFactory {
    @Override
    public UserDao createDAO() {
        return UserDaoJDBCImpl.getInstance();
    }
}
