package org.pdla.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionManagementTest {

    private static int mission_test_id;
    private static int user_test_id;
    private static int user_test_id2;
    private static int connection_test_id;

    @BeforeAll
    static void setup() {
        Credentials.SetTestCredentials();
        UserManagement userManagement = new UserManagement();
        userManagement.CreateUser("TestUser", "TestPassword", "volunteer");
        user_test_id = UserManagement.getID("TestUser");
        userManagement.CreateUser("TestUser2", "TestPassword", "volunteer");
        user_test_id2 = UserManagement.getID("TestUser2");
        MissionManagement.createMission("TestMission", user_test_id, "help");
        mission_test_id = MissionManagement.getLastCreatedMissionId();
    }

    @AfterAll
    static void clear() {
        ConnectionManagement.DeleteConnection(connection_test_id);
        MissionManagement.deleteMission(mission_test_id);
        UserManagement.DeleteUser("TestUser");
        UserManagement.DeleteUser("TestUser2");
    }

    @org.junit.jupiter.api.Test
    void joinMission() {
        assertDoesNotThrow(() -> ConnectionManagement.joinMission(mission_test_id, user_test_id, "help"));
        connection_test_id = ConnectionManagement.getLastCreatedConnection();
        assertTrue(connection_test_id > 0); // Vérifie que la connexion a bien été créée
    }

    @org.junit.jupiter.api.Test
    void getUserConnectedToMission() {
        List<String> connectedUsers = ConnectionManagement.getUserConnectedToMission(mission_test_id, "help");
        assertTrue(connectedUsers.contains("TestUser"));
    }

    @org.junit.jupiter.api.Test
    void isUserInMission_True() {
        assertTrue(ConnectionManagement.isUserInMission(mission_test_id, user_test_id));
    }

    @org.junit.jupiter.api.Test
    void isUserInMission_False() {
        assertFalse(ConnectionManagement.isUserInMission(mission_test_id, 999999)); // ID utilisateur inexistant
    }

    @org.junit.jupiter.api.Test
    void deleteConnection() {
        assertDoesNotThrow(() -> ConnectionManagement.joinMission(mission_test_id, user_test_id2, "help"));
        int lastConnectionId = ConnectionManagement.getLastCreatedConnection();
        assertDoesNotThrow(() -> ConnectionManagement.DeleteConnection(lastConnectionId));
        assertFalse(ConnectionManagement.isUserInMission(mission_test_id, user_test_id2));
    }
}
