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

    public static int getLastCreatedMissionId() {
        int missionId = -1; // Valeur par défaut si l'ID ne peut pas être récupéré
        String selectSQL = "SELECT mission_id FROM missions ORDER BY mission_id DESC LIMIT 1";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                missionId = resultSet.getInt("mission_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return missionId;
    }

    public static int getID(String description) {
        String SQL = "SELECT mission_id FROM missions WHERE description = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, description);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("mission_id");
                } else {
                    System.out.println("Error : Mission not found"); // Ou gérer autrement si l'utilisateur n'existe pas
                    System.exit(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting the ID corresponding to a mission in the database.");
            e.printStackTrace();
            System.exit(1);
        }
        return -1;
    }

    public static boolean missionExists(int mission_id) {
        String SQL = "SELECT * FROM missions WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();
        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, mission_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking if a mission exists in the database.");
            e.printStackTrace();
        }
        return false;
    }

    public static void terminateMission(int mission_id) {
        String query = "UPDATE missions SET mission_state = ? WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();

        try (PreparedStatement preparedStatement = dbconn.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, "realized");
            preparedStatement.setInt(2, mission_id);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mission terminated successfully.");
            } else {
                System.out.println("Failed to terminate mission.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la terminaison de la mission : " + e.getMessage());
        }
    }

    public static void deleteMission(int mission_id) {
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

    public static String getMissionState(int mission_id) {
        String selectSQL = "SELECT mission_state FROM missions WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();

        try (Connection connection = dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setInt(1, mission_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   return resultSet.getString("mission_state");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error selecting mission state from the database: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static void validateMission(int mission_id) {
        String query = "UPDATE missions SET mission_state = ? WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();

        try (PreparedStatement preparedStatement = dbconn.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, "valid");
            preparedStatement.setInt(2, mission_id);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mission validate successfully.");
            } else {
                System.out.println("Failed to validate mission.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la validation de la mission : " + e.getMessage());
        }
    }

    public static void refuseMission(int mission_id, String reason) {
        String query = "UPDATE missions SET mission_state = ?, motif = ? WHERE mission_id = ?";
        DatabaseConnection dbconn = new DatabaseConnection();

        try (PreparedStatement preparedStatement = dbconn.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, "refused");
            preparedStatement.setString(2, reason);
            preparedStatement.setInt(3, mission_id);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mission refused successfully.");
            } else {
                System.out.println("Failed to refuse the mission.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la validation de la mission : " + e.getMessage());
        }
    }
}
