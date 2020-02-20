package org.user.service;

import org.user.model.User;

import java.util.List;

public interface UserService {

    void insertUser(User user);

    User selectUser(int id);

    List <User> selectAllUsers();

    void deleteUser(int id);

    void updateUser(User user);

    boolean userIsExist(String login, String password);

    String getRoleByLoginPassword(String login, String password);

    User getUserByLoginAndPassword(String login, String password);
}
