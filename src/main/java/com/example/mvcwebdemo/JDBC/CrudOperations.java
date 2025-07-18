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
        System.out.println("Read all books");
    }
    public static void readBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Read books by genre");
    }
    public static void paginateBooks(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Paginate books by price");
    }
    public static void updateBookPrice(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Update book price");
    }
    public static void deleteBooksByGenre(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Delete books by genre");
    }
    public static void callAddBookProcedure(Connection conn) throws SQLException {
        System.out.println("------------------------");
        System.out.println("Calling stored procedure AddBook");
    }
    public static void simulateSQLError(Connection conn) {
        System.out.println("------------------------");
        System.out.println("Simulating SQL Error");
    }
}
