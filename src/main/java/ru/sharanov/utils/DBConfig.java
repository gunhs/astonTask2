package ru.sharanov.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static String dbDriver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/hotel";
    private static String user = "gunhs";
    private static String password = "kopo6ok";

    public static Connection connection() {
        Connection connection;
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
