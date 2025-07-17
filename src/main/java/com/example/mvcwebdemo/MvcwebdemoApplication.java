package com.example.mvcwebdemo;

import java.sql.*;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcwebdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcwebdemoApplication.class, args);

        String url = "jdbc:mysql://localhost:3306/MY_DATABASE"; // Change to your database
        String user = "root"; // Your database username
        String password = "admin"; // Your database password

        try {
            // Establishing connection
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            System.out.println("connection successful!");
            // Your JDBC code here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
