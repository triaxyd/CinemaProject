package com.triaxyd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;


public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javakratiseis";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            //class not found
        } catch (SQLException e) {
            //Error at database
        }

        return connection;
    }

}
