package org.user.dao;

import org.user.model.User;
import org.user.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC implements UserDAO {
    private Connection connection = DBHelper.getDbHelper().getConnection();
    private static UserDAOJDBC daoJDBC = new UserDAOJDBC();

    private UserDAOJDBC() {
    }

    public static UserDAOJDBC getInstance() {
        return daoJDBC;
    }

    @Override
    public void insertUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users" +
                "  (id, name, email, country) VALUES  (null, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email,country" +
                " from users where id =?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    @Override
    public List <User> selectAllUsers() {
        List <User> users = new ArrayList <>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from users where id = ?")) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("update users set name = ?, email= ?, " +
                "country =? where id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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