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

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
            while (resultSet.next()) {
                System.out.println("Student ID: " + resultSet.getInt("StudentID"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Major: " + resultSet.getString("Major"));
            }

            resultSet = statement.executeQuery(
                    "SELECT * FROM Students WHERE Major = 'Computer Science'"
            );
            System.out.println("Students with major of Computer Science");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }

            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC"
            );
            System.out.println("Printing students with descending GPAs");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }

            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC LIMIT 5"
            );
            System.out.println("Limiting resultset to 5");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
