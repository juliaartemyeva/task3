package org.user.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.user.model.User;
import org.user.util.*;

import java.util.List;

import static java.util.Objects.nonNull;

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
        session.save(new User(user.getLogin(), user.getPassword(), user.getCountry(), user.getRole()));
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

        @Override
        public boolean userIsExist(String login, String password) {
            Session session = sessionFactory.openSession();
            List <User> user = session.createQuery("from User where login = '"+login+"' and password = '"+password+"'", User.class).list();
        session.close();
        return user.size() == 1;

    }

    @Override
    public String getRoleByLoginPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        List <User> user = session.createQuery("from User where login = '"+login+"' " +
                "and password = '"+password+"'", User.class).list();
        session.close();
        return user.get(0).getRole();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        List <User> user = session.createQuery("from User where login = '"+login+"' " +
                "and password = '"+password+"'", User.class).list();
        session.close();
        return user.get(0);
    }
}
