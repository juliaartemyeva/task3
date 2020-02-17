package org.user.dao;

import org.user.model.User;

import java.util.List;

public interface UserDao {

    void insertUser(User user);

    User selectUser(int id);

    List <User> selectAllUsers();

    void deleteUser(int id);

    void updateUser(User user);
}
