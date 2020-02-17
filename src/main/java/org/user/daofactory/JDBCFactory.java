package org.user.daofactory;

import org.user.dao.UserDAO;
import org.user.dao.UserDAOJDBC;

public class JDBCFactory implements DAOFactory {
    @Override
    public UserDAO createDAO() {
        return UserDAOJDBC.getInstance();
    }
}
