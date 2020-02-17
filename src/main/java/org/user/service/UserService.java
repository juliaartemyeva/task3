package org.user.service;

import org.user.model.User;

import java.util.List;

public interface UserService {

    void insertUser(User user);

    User selectUser(int id);

    List <User> selectAllUsers();

    void deleteUser(int id);

    void updateUser(User user);
}
