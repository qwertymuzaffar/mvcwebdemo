package com.example.mvcwebdemo.JDBC;

import java.sql.*;

public class Jdbc {

    public static void SQLQueries() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/MY_DATABASE"; // Change to your database
        String user = "root"; // Your database username
        String password = "admin"; // Your database password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Establishing connection
            Statement statement = conn.createStatement();
            System.out.println("connection successful!");


            // Retrieve information using the SELECT command
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
            while (resultSet.next()) {
                System.out.println("Student ID: " + resultSet.getInt("StudentID"));
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("Major: " + resultSet.getString("Major"));
            }

            // Filter Data with WHERE Clause
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students WHERE Major = 'Computer Science'"
            );
            System.out.println("Students with major of Computer Science");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }

            // Sort results with ORDER BY
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC"
            );
            System.out.println("Printing students with descending GPAs");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }

            // Limit Results
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students ORDER BY GPA DESC LIMIT 5"
            );
            System.out.println("Limiting resultset to 5");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }

            // Aggregate data with GROUP BY
            resultSet = statement.executeQuery(
                    "SELECT Major, COUNT(*) AS StudentCount FROM Students GROUP BY Major"
            );
            System.out.println("Count students by major");
            while (resultSet.next()) {
                System.out.println("Major: " + resultSet.getString("Major"));
                System.out.println("Number of Students: " + resultSet.getInt("StudentCount"));
            }

            // Join tables
            resultSet = statement.executeQuery(
                    "SELECT Students.Name, Courses.CourseName " +
                            "FROM Students " +
                            "INNER JOIN Enrollments ON Students.StudentID = Enrollments.StudentID " +
                            "INNER JOIN Courses ON Enrollments.CourseID = Courses.CourseID"
            );

            System.out.println("Retrieve Student Names and Their Courses");

            while (resultSet.next()) {
                System.out.println("Student: " + resultSet.getString("Name"));
                System.out.println("Course: " + resultSet.getString("CourseName"));
            }


            // Handle NULL values
            resultSet = statement.executeQuery(
                    "SELECT * FROM Students WHERE Major IS NULL"
            );
            System.out.println("Retrieve Students Without a Declared Major");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
        }
    }
}
