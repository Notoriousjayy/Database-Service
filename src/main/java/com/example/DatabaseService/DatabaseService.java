package com.example.DatabaseService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
    private static final String DB_URL = "jdbc:mysql://your-rds-endpoint:3306/your-database-name";
    private static final String DB_USER = "your-db-username";
    private static final String DB_PASSWORD = "your-db-password";

    public void connectToDatabase() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Execute SQL queries here

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeQuery(String sqlQuery) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set parameters if needed
            // preparedStatement.setString(1, "parameter value");

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String sqlUpdate) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);

            // Set parameters if needed
            // preparedStatement.setString(1, "parameter value");

            int rowsAffected = preparedStatement.executeUpdate();

            // Process the update result

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

