package org.pdla.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class MissionManagementTest {

    private static MissionManagement missionManagement;
    private static UserManagement userManagement;
    private static int mission_test_id;
    private static int user_test_id;

    @BeforeAll
    static void setup() {
        Credentials.SetTestCredentials();

        userManagement = new UserManagement();
        userManagement.CreateUser("AAA", "AAA", "volunteer");
        user_test_id = userManagement.getID("AAA");

        missionManagement = new MissionManagement();
        missionManagement.createMission("AAA", user_test_id, "help");
        mission_test_id = MissionManagement.getLastCreatedMissionId();
    }

    @AfterAll
    static void Clear() {
        MissionManagement.deleteMission(mission_test_id); // on supprimer la mission
        userManagement.DeleteUser("AAA"); // on supprime l'utilisateur
    }

    @org.junit.jupiter.api.Test
    void createMission() {
        assertDoesNotThrow(() -> missionManagement.createMission("BBB", user_test_id, "help"));
        MissionManagement.deleteMission(MissionManagement.getLastCreatedMissionId()); // on supprimer la mission
    }

    @org.junit.jupiter.api.Test
    void deleteMission() {
        missionManagement.createMission("CCC", 1, "help");
        int mission_id = MissionManagement.getLastCreatedMissionId();
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
    void getMissionState() {
        assertDoesNotThrow(() -> MissionManagement.getMissionState(mission_test_id));
        assertEquals("waiting", MissionManagement.getMissionState(mission_test_id));
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
