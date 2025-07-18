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

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
            insertStudent(conn);
            updateStudent(conn);
            getStudentsByMajor(conn);
            callGetStudentsByMajor(conn);
            callGetAverageGPA(conn);
            insertStudentWithGeneratedKey(conn);
            simulateSQLError(conn);
            System.out.println("------------------------");
            System.out.println("Finish!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert data into the Students table");
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Students (Name, Major, GPA) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, "Alice Johnson");
            preparedStatement.setString(2, "Computer Science");
            preparedStatement.setDouble(3, 3.8);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        }
    }

    public static void updateStudent(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Update Students with GPA of 3.9 and name of Alice");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE Students SET GPA = ? WHERE Name = ?")) {
            preparedStatement.setDouble(1, 3.9);
            preparedStatement.setString(2, "Alice Johnson");

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
        }
    }

    public static void getStudentsByMajor(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Get all students with major of Computer Science");
    }
    public static void callGetStudentsByMajor(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetStudentsByMajor");
    }
    public static void callGetAverageGPA(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetAverageGPA");
    }
    public static void insertStudentWithGeneratedKey(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert student and retrieve generated key");
    }
    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");
    }

}
