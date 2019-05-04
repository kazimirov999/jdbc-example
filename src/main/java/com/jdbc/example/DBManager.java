package com.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBManager {

    private final String url;
    private final String user;
    private final String password;

    private static DBManager dbManager;
    private static Connection connection;

    private DBManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DBManager init(String url, String user, String password) {
        if (dbManager == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            dbManager = new DBManager(url, user, password);
        }
        return dbManager;
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            throw new RuntimeException("DB Manager was not initialized !!!");
        }
        return dbManager;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}