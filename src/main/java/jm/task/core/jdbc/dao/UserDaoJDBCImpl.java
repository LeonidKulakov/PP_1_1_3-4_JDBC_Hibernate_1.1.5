package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ( id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL);";
        try (Connection connection = Util.getConnection(); var statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";
        try (Connection connection = Util.getConnection(); var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS(name, lastName,age) VALUES (?,?,?)";
        try (Connection connection = Util.getConnection(); var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем — %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = Util.getConnection(); var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection(); var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(
                        new User(
                                resultSet.getString("name"),
                                resultSet.getString("lastName"),
                                resultSet.getByte("age")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        try (Connection connection = Util.getConnection(); var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
