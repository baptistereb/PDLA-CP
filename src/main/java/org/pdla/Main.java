package org.pdla;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbconn= new DatabaseConnection();
        String insertSQL = "INSERT INTO users (pseudo, password, user_type) VALUES (?, ?, ?)";

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, "test");
            preparedStatement.setString(2, "TRACS2024");
            preparedStatement.setString(3, "moderator");

            // Execute the command
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting user into the database.");
            e.printStackTrace();
        }
        dbconn.closeConnection();
    }

}