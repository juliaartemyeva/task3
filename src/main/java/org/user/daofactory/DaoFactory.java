package org.user.daofactory;

import org.user.dao.UserDao;

import java.util.ResourceBundle;

public abstract class DaoFactory {
    private static ResourceBundle prop = ResourceBundle.getBundle("config");
    private static String daoName = prop.getString("dao");

    public abstract UserDao createDAO();

    public static DaoFactory getDaoFactory() {
        DaoFactory factory = null;
        if (daoName.equalsIgnoreCase("Hibernate")) {
            factory = new HibernateFactory();
        } else if (daoName.equalsIgnoreCase("JDBC")) {
            factory = new JDBCFactory();
        }
        return factory;
    }
}
