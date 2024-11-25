package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserManagement {

    private static int user_id = 0;

    public void CreateUser(String username, String password, String user_type) {
        String insertSQL = "INSERT INTO users (pseudo, password, user_type) VALUES (?, ?, ?)";
        DatabaseConnection dbconn = new DatabaseConnection();
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
        DatabaseConnection dbconn = new DatabaseConnection();
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
        String SQL = "SELECT user_id,password FROM users WHERE pseudo = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if(resultSet.getString("password").equals(password)){
                        user_id = resultSet.getInt("user_id");
                        return true;
                    } else {
                        return false;
                    }
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

    public static int getMyID() {
        return user_id;
    }


    public static String getPseudo(int user_id) {
        String SQL = "SELECT pseudo FROM users WHERE user_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, user_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("pseudo");
                } else {
                    System.out.println("Error : User not found"); // Ou gérer autrement si l'utilisateur n'existe pas
                    System.exit(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting the pseudo corresponding to a user in the database.");
            e.printStackTrace();
            System.exit(1);
        }
        return "Error";
    }


    public boolean UserExists(String username) {
        String SQL = "SELECT * FROM users WHERE pseudo = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking if a user exists in the database.");
            e.printStackTrace();
        }
        return false;
    }

    public String getUserType(String username) {
        String SQL = "SELECT user_type FROM users WHERE pseudo = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("user_type");
                } else {
                    System.out.println("Error : User not found"); // Ou gérer autrement si l'utilisateur n'existe pas
                    System.exit(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking the user type in the database.");
            e.printStackTrace();
            System.exit(1);
        }
        return "Error";
    }


}