package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String tableName = "users";
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + tableName +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , name VARCHAR(35) NOT NULL, " +
                    "lastName VARCHAR(35) NOT NULL, age MEDIUMINT NOT NULL) DEFAULT CHARSET=utf8");
        } catch (Exception e) {
            System.err.println("Не удалось создать таблицу.");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + tableName);
        } catch (Exception e) {
            System.err.println("Не удалось удалить таблицу.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + tableName + "(name , lastName, age) " +
                    " values ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User с именем " + name + " добавлен в базу данных.");
        } catch (Exception e) {
            System.err.println("Не удалось сохранить пользоваетеля.");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id);
        } catch (Exception e) {
            System.err.println("Не удалось удалить пользователя.");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT  * FROM " + tableName);

            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4));
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Не удалось вернуть список пользователей.");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
        } catch (Exception e) {
            System.err.println("Не удалось очистить таблицу.");
        }
    }
}
