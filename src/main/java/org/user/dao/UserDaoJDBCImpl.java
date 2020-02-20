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
                "  (id, login, password, country) VALUES  (null, ?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCountry());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("select login,password,country" +
                " from user where id =?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String country = rs.getString("country");
                user = new User(id, login, password, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
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
                users.add(new User(id, login, password, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from user where id = ?")) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("update user set login = ?, password= ?, " +
                "country =? where id = ?")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
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
        try (PreparedStatement statement = connection.prepareStatement("select *" +
                " from user where login =? and password=?")) {
            statement.setString(1, login);
            statement.setString(1, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country = rs.getString("country");
                user = new User(id, login, password, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}