package com.self.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection createConnection()
    {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";


        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}