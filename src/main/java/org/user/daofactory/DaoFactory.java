package org.user.daofactory;

import org.user.dao.UserDao;

public interface DaoFactory {
    UserDao createDAO();
}
