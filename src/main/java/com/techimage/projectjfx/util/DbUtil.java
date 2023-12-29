package com.techimage.projectjfx.util;

import com.techimage.projectjfx.MainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    public static Connection dbConnect () {
        try {
            Class.forName("org.sqlite.JDBC");
            String path = MainApp.class.getResource("db/database.db").getPath();
            String url = String.format("jdbc:sqlite:%s", path.substring(1));
            return connection = DriverManager.getConnection(url);
           // System.out.println("Connection success!");
        }
        catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public  static void dbDisconnect(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}

