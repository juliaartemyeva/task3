package org.user.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.user.model.User;
import org.user.util.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();
    private SessionFactory sessionFactory = DBHelper.getDbHelper().getSessionFactory();

    private UserDaoHibernateImpl() {
    }

    public static UserDaoHibernateImpl getInstance() {
        return daoHibernate;
    }

    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(user.getName(), user.getEmail(), user.getCountry()));
        transaction.commit();
        session.close();
    }

    @Override
    public User selectUser(int id) {
        Session session = sessionFactory.openSession();
        User user = session.createQuery("select user from User user where " +
                "user.id = '" + id + "'", User.class).getSingleResult();
        session.close();
        return user;
    }

    @Override
    public List <User> selectAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <User> users = session.createQuery("select user from User user", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = selectUser(id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
