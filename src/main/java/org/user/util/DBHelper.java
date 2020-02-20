package org.user.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.user.model.User;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static DBHelper dbHelper = new DBHelper();
    private static SessionFactory sessionFactory;
    private String URL_DBNAME = getProp().getProperty("URL/dbName");
    private String USER = getProp().getProperty("user");
    private String PASSWORD = getProp().getProperty("password");
    private String SERVERTIMEZONE = getProp().getProperty("serverTimezone");

    private DBHelper() {
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private Properties getProp() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", URL_DBNAME);
        configuration.setProperty("hibernate.connection.username", USER);
        configuration.setProperty("hibernate.connection.password", PASSWORD);
        configuration.setProperty("hibernate.connection.serverTimezone", SERVERTIMEZONE);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append(URL_DBNAME).             //URL and db name
                    append("user=").
                    append(USER).                   //login
                    append("&password=").
                    append(PASSWORD).               //password
                    append("&serverTimezone=").
                    append(SERVERTIMEZONE);         //serverTimeZone
            connection = DriverManager.getConnection(url.toString());
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
