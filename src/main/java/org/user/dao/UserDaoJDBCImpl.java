package org.user.dao;

import org.user.model.User;
import org.user.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = DBHelper.getDbHelper().getConnection();
    private static UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

    private UserDaoJDBCImpl() {
    }

    public static UserDaoJDBCImpl getInstance() {
        return daoJDBC;
    }

    @Override
    public void insertUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user" +
                "  (id, login, password, country, role) VALUES  (null, ?, ?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCountry());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("select login,password,country, role" +
                " from user where id =?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String country = rs.getString("country");
                String role = rs.getString("role");
                user = new User(id, login, password, country, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List <User> selectAllUsers() {
        List <User> users = new ArrayList <>();
        try (PreparedStatement statement = connection.prepareStatement("select * from user")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String country = rs.getString("country");
                String role = rs.getString("role");
                users.add(new User(id, login, password, country, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from user where id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("update user set login = ?, password= ?, " +
                "country =?, role =? where id = ?")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCountry());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean userIsExist(String login, String password) {
        try(PreparedStatement statement = connection.prepareStatement("select id from user where login =? and password =?")) {
            statement.setString(1,login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getRoleByLoginPassword(String login, String password) {
        String role = null;
        try(PreparedStatement statement = connection.prepareStatement("select role from user where login =? and password =?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                role = set.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("select id, country, role" +
                " from user where login =? and password=?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country = rs.getString("country");
                String role = rs.getString("role");
                user = new User(id, login, password, country, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isLoginExists(String login) {
        try(PreparedStatement statement = connection.prepareStatement("select id from user where login =?")) {
            statement.setString(1,login);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}