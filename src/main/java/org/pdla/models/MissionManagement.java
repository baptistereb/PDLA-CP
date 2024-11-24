package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissionManagement {
    public static void createMission(String description, int user_id, String mission_type) {
        String insertSQL = "INSERT INTO missions (description, user_id, mission_state, mission_type) VALUES (?, ?, ?, ?)";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, description);
            preparedStatement.setString(2, String.valueOf(user_id));
            preparedStatement.setString(3, "waiting");
            preparedStatement.setString(4, mission_type);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Mission inserted successfully.");
            } else {
                System.out.println("Failed to insert mission.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting the mission into the database.");
            e.printStackTrace();
        }
        dbconn.closeConnection();
    }

    public void deleteMission(int mission_id) {
        String deleteSQL = "DELETE FROM missions WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, String.valueOf(mission_id));

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

    public List<List<String>> getMissionsbystate(String mission_state) {
        String selectSQL = "SELECT * FROM missions WHERE mission_state = '" + mission_state + "'";
        DatabaseConnection dbconn = new DatabaseConnection();

        List<List<String>> missions = new ArrayList<>();

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                missions.add(Arrays.asList(
                        resultSet.getString("mission_id"),
                        resultSet.getString("description"),
                        resultSet.getString("user_id"),
                        resultSet.getString("mission_state"),
                        resultSet.getString("mission_type")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error selecting missions from the database: " + e.getMessage());
            e.printStackTrace();
        }

        return missions;
    }

    public List<List<String>> getMissionsbytype(String mission_type) {
        String selectSQL = "SELECT * FROM missions WHERE mission_type = '" + mission_type + "'";
        DatabaseConnection dbconn = new DatabaseConnection();

        List<List<String>> missions = new ArrayList<>();

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                missions.add(Arrays.asList(
                        resultSet.getString("mission_id"),
                        resultSet.getString("description"),
                        resultSet.getString("user_id"),
                        resultSet.getString("mission_state"),
                        resultSet.getString("mission_type")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error selecting missions from the database: " + e.getMessage());
            e.printStackTrace();
        }

        return missions;
    }

}
