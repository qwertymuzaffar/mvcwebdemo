package com.example.mvcwebdemo.JDBC;

import java.sql.*;

public class CrudOperations {

    public CrudOperations() {
        String url = "jdbc:mysql://localhost:3306/MY_DATABASE"; // Change to your database
        String user = "root"; // Your database username
        String password = "admin"; // Your database password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
            insertBook(conn);
            readAllBooks(conn);
            readBooksByGenre(conn);
            paginateBooks(conn);
            updateBookPrice(conn);
            deleteBooksByGenre(conn);
            callAddBookProcedure(conn);
            simulateSQLError(conn);
            System.out.println("------------------------");
            System.out.println("Finish!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBook(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Inserting a book using PreparedStatement");
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO books (Title, Author, Price, Genre, PublicationDate, PublisherID) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, "Advanced Java");
            ps.setString(2, "Jane Doe");
            ps.setDouble(3, 45.99);
            ps.setString(4, "Programming");
            ps.setDate(5, java.sql.Date.valueOf("2023-01-10"));
            ps.setInt(6, 1);
            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        }
    }

    public static void readAllBooks(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Retrieving all books");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
                System.out.println("Author: " + rs.getString("Author"));
                System.out.println("Price: " + rs.getDouble("Price"));
            }
        }
    }

    public static void readBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Retrieving books by genre: Programming");
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM books WHERE Genre = ?")) {
            ps.setString(1, "Programming");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
            }
        }
    }

    public static void paginateBooks(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Paginating books by price (LIMIT 5 OFFSET 0)");
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM books ORDER BY Price DESC LIMIT 5 OFFSET 0")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title"));
                System.out.println("Price: " + rs.getDouble("Price"));
            }
        }
    }

    public static void updateBookPrice(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Updating book price");
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE books SET Price = ? WHERE Title = ?")) {
            ps.setDouble(1, 49.99);
            ps.setString(2, "Advanced Java");
            int rows = ps.executeUpdate();
            System.out.println("Rows updated: " + rows);
        }
    }

    public static void deleteBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Deleting books in genre: Programming");

        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM books WHERE Genre = ?")) {
            ps.setString(1, "Programming");

            int rows = ps.executeUpdate();
            System.out.println("Rows deleted: " + rows);
        }
    }

    public static void callAddBookProcedure(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure AddBook");

        try (CallableStatement cs = conn.prepareCall("{CALL AddBook(?, ?, ?, ?, ?, ?)}")) {
            cs.setString(1, "Advanced Java");
            cs.setString(2, "Jane Doe");
            cs.setDouble(3, 45.99);
            cs.setString(4, "Programming");
            cs.setDate(5, java.sql.Date.valueOf("2023-01-10"));
            cs.setInt(6, 1);

            cs.execute();
            System.out.println("Book added successfully using stored procedure.");
        }
    }

    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");
    }
}
