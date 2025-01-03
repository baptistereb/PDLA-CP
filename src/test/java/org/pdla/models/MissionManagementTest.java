package org.pdla.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

public class MissionManagementTest {

    private static UserManagement userManagement;
    private static int mission_test_id;
    private static int user_test_id;

    @BeforeAll
    static void setup() {
        Credentials.SetTestCredentials();

        userManagement = new UserManagement();
        userManagement.CreateUser("AAA", "AAA", "volunteer");
        user_test_id = UserManagement.getID("AAA");

        MissionManagement.createMission("AAA", user_test_id, "help");
        mission_test_id = MissionManagement.getLastCreatedMissionId();
    }

    @AfterAll
    static void Clear() {
        MissionManagement.deleteMission(mission_test_id); // on supprimer la mission
        UserManagement.DeleteUser("AAA"); // on supprime l'utilisateur
    }

    @org.junit.jupiter.api.Test
    void createMission() {
        assertDoesNotThrow(() -> MissionManagement.createMission("BBB", user_test_id, "help"));
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId()); // on supprimer la mission
    }

    @org.junit.jupiter.api.Test
    void deleteMission() {
        MissionManagement.createMission("CCC", user_test_id, "help");
        int mission_id = MissionManagement.getID("CCC");
        assertDoesNotThrow(() -> MissionManagement.deleteMission(mission_id));
        assertFalse(MissionManagement.missionExists(mission_id));
    }

    @org.junit.jupiter.api.Test
    void missionExists() {
        assertTrue(MissionManagement.missionExists(mission_test_id));
    }

    @org.junit.jupiter.api.Test
    void missionDoesNotExists() {
        assertFalse(MissionManagement.missionExists(333333333));
    }

    @org.junit.jupiter.api.Test
    void terminateMission() {
        assertDoesNotThrow( () -> MissionManagement.terminateMission(mission_test_id));
        assertEquals("realized", MissionManagement.getMissionState(mission_test_id));
    }

    @org.junit.jupiter.api.Test
    void validateMission() {
        assertDoesNotThrow( () -> MissionManagement.validateMission(mission_test_id));
        assertEquals("valid", MissionManagement.getMissionState(mission_test_id));
    }

    @org.junit.jupiter.api.Test
    void refuseMission() {
        assertDoesNotThrow( () -> MissionManagement.refuseMission(mission_test_id, "No reason at all ;)"));
        assertEquals("refused", MissionManagement.getMissionState(mission_test_id));
    }

}
