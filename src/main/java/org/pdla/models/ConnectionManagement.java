package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManagement {
    public static void joinMission(int mission_id, int myID, String connection_type) {
        String insertSQL = "INSERT INTO connections (mission_id, user_id, connection_type) VALUES (?, ?, ?)";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setInt(1, mission_id);
            preparedStatement.setInt(2, myID);
            preparedStatement.setString(3, connection_type);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Join the mission inserted successfully.");
            } else {
                System.out.println("Failed to join the mission.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting connection into the database.");
            e.printStackTrace();
        }
        dbconn.closeConnection();
    }

    public static List<String> getUserConnectedToMission(int mission_id, String type) {
        String selectSQL = "SELECT u.pseudo FROM connections c JOIN users u ON c.user_id = u.user_id WHERE c.mission_id = ? AND c.connection_type = ?";

        DatabaseConnection dbconn = new DatabaseConnection();
        List<String> userPseudos = new ArrayList<>();

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, mission_id);
            preparedStatement.setString(2, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Ajouter les pseudos des utilisateurs à la liste
                    userPseudos.add(resultSet.getString("pseudo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching connected users for the mission.");
            e.printStackTrace();
        } finally {
            dbconn.closeConnection();
        }

        return userPseudos;
    }

    public static boolean isUserInMission(int mission_id, int user_id) {
        String query = "SELECT 1 FROM connections WHERE mission_id = ? AND user_id = ? " +
                "UNION " +
                "SELECT 1 FROM missions WHERE mission_id = ? AND user_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Remplissage des paramètres
            preparedStatement.setInt(1, mission_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setInt(3, mission_id);
            preparedStatement.setInt(4, user_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Si un résultat est trouvé, cela signifie que la condition est vraie
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking if user is in mission: " + e.getMessage());
            e.printStackTrace();
        } finally {
            dbconn.closeConnection();
        }

        return false; // Aucun résultat trouvé, donc l'utilisateur n'est pas associé
    }

    public static void DeleteConnection(int connection_id) {
        //on delete la connection
        String deleteSQL = "DELETE FROM connections WHERE connection_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, String.valueOf(connection_id));

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Mission deleted successfully.");
            } else {
                System.out.println("Failed to delete mission.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting mission from the database.");
            e.printStackTrace();
        }

    }
    public static int getLastCreatedConnection(){
        String selectSQL = "SELECT connection_id FROM connections ORDER BY connection_id DESC LIMIT 1";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            int connection_id = 0;
            preparedStatement.execute();
            var resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                connection_id = resultSet.getInt("connection_id");
            }
            return connection_id;
        } catch (SQLException e) {
            System.out.println("Error getting last created connection from the database.");
            e.printStackTrace();
            return 0;
        }
    }

}
