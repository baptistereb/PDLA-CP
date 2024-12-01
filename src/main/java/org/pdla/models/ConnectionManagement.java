package org.pdla.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManagement {

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
