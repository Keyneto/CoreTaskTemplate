package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/testTwo?characterEncoding=utf-8&useSSL=false";
    private String userName = "root";
    private String password  = "1234567";

    public Connection getConnection() throws Exception {
        connection = DriverManager.getConnection(url, userName, password);
        Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        return connection;
    }
}
