package org.user.daofactory;

import org.user.dao.UserDao;
import org.user.dao.UserDaoJDBCimpl;

public class JDBCFactory implements DaoFactory {
    @Override
    public UserDao createDAO() {
        return UserDaoJDBCimpl.getInstance();
    }
}
