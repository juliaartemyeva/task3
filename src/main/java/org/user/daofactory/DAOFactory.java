package org.user.daofactory;

import org.user.dao.UserDAO;

public interface DAOFactory {
    UserDAO createDAO();
}
