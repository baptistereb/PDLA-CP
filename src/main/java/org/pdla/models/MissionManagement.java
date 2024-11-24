package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
