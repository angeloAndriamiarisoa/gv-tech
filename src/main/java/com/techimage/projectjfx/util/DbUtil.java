package com.techimage.projectjfx.util;

import com.techimage.projectjfx.MainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private Connection connection;
    public void dbConnect () {
        try {
            Class.forName("org.sqlite.JDBC");
            String path = MainApp.class.getResource("db/database.db").getPath();
            String url = String.format("jdbc:sqlite:%s", path.substring(1));
            this.connection = DriverManager.getConnection(url);
            System.out.println("Connection success!");
        }
        catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public  void dbDisconnect(){
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}

