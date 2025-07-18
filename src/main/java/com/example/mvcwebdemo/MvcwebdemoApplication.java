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

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM Students WHERE Major = ?")) {
            preparedStatement.setString(1, "Computer Science");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }
        }
    }

    public static void callGetStudentsByMajor(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetStudentsByMajor");

        try (CallableStatement callableStatement = conn.prepareCall("{CALL GetStudentsByMajor(?)}")) {
            callableStatement.setString(1, "Computer Science");

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
                System.out.println("GPA: " + resultSet.getDouble("GPA"));
            }
        }
    }

    public static void callGetAverageGPA(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure GetAverageGPA");
        try (CallableStatement callableStatement = conn.prepareCall("{CALL GetAverageGPA(?)}")) {
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.execute();
            double avgGPA = callableStatement.getDouble(1);
            System.out.println("Average GPA: " + avgGPA);
        }
    }

    public static void insertStudentWithGeneratedKey(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Insert student and retrieve generated key");

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO Students (Name, Major, GPA) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setString(2, "Mathematics");
            preparedStatement.setDouble(3, 3.6);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newStudentID = generatedKeys.getInt(1);
                System.out.println("Generated Student ID: " + newStudentID);
            }
        }
    }

    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");
    }

}
