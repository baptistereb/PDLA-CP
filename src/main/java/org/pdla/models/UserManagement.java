package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserManagement {
    public void CreateUser(String username, String password, String user_type) {
        String insertSQL = "INSERT INTO users (pseudo, password, user_type) VALUES (?, ?, ?)";
        DatabaseConnection dbconn= new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, user_type);

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

    public void DeleteUser(String username) {
        String deleteSQL = "DELETE FROM users WHERE pseudo = ?";
        DatabaseConnection dbconn= new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, username);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting user from the database.");
            e.printStackTrace();
        }
        dbconn.closeConnection();
    }

    public boolean Login(String username, String password) {
        String SQL = "SELECT password FROM users WHERE pseudo = ?";
        DatabaseConnection dbconn= new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password").equals(password);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting the password corresponding to a user in the database.");
            e.printStackTrace();
        }
        dbconn.closeConnection();
        return false;
    }
}
