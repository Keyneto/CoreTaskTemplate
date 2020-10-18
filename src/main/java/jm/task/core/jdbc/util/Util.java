package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/testTwo?characterEncoding=utf-8&useSSL=false";
    private String userName = "root";
    private String password  = "1234567";
    private static SessionFactory sessionFactory;

    public Connection getConnection() throws Exception {
        connection = DriverManager.getConnection(url, userName, password);
        Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testTwo?characterEncoding=utf-8&useSSL=false")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "1234567")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
