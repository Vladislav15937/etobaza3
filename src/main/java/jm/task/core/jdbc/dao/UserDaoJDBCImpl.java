package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Util util = new Util();
    Statement statement = null;
    Connection connection = null;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            statement = connection.createStatement();
            statement.executeUpdate("create table users" +
                    "(" +
                    "    name     varchar(100) null," +
                    "    lastName varchar(100) null," +
                    "    age      int          null," +
                    "    id       int auto_increment" +
                    "        primary key" +
                    ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public void dropUsersTable() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT users (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

    public void removeUserById(long id) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            statement = connection.createStatement();
            int rows = statement.executeUpdate("DELETE FROM users WHERE id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


    public List<User> getAllUsers() {
        String sql = "SELECT name, lastName, age, id FROM users";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return userList;
    }


    public void cleanUsersTable() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baza_kata", "root", "roote");
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
